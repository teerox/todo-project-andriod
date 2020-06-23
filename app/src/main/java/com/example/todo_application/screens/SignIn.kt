package com.example.todo_application.screens

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.todo_application.MyApplication

import com.example.todo_application.R
import com.example.todo_application.databinding.ActivityMainBinding
import com.example.todo_application.databinding.SigninBinding

import com.example.todo_application.model.Todo
import com.example.todo_application.model.TodoItems
import com.example.todo_application.screens.todoviewmodel.TodoViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.Executor
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class SignIn : AppCompatActivity() {

    private lateinit var binding:SigninBinding
    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    @Inject
    lateinit var todoViewModel: TodoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (this.application as MyApplication).getSharedComponent().inject(this)
        // Inflate the layout for this fragment
       // binding = DataBindingUtil.inflate(inflater,R.layout.signin, container, false)
       binding = DataBindingUtil.setContentView<SigninBinding>(this,R.layout.signin)
        executor = ContextCompat.getMainExecutor(this)
        biometricPrompt = BiometricPrompt(this, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int,
                                                   errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    Toast.makeText(applicationContext,
                        "Authentication error: $errString", Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    Toast.makeText(applicationContext,
                        "Authentication succeeded!", Toast.LENGTH_SHORT)
                        .show()
                    val intent = Intent(applicationContext,MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Toast.makeText(applicationContext, "Authentication failed",
                        Toast.LENGTH_SHORT)
                        .show()
                }
            })

        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric login for my app")
            .setSubtitle("Log in using your biometric credential")
            .setNegativeButtonText("Use account password")
            .build()

        // Prompt appears when user clicks "Log in".
        // Consider integrating with the keystore to unlock cryptographic operations,
        // if needed by your app.
        val biometricLoginButton = binding.biometricLogin
        biometricLoginButton.setOnClickListener {
            biometricPrompt.authenticate(promptInfo)
        }

        todoViewModel.hideView(binding.wrongEmail,binding.wrongPassword)
//remember user login
        val sharedPref = this.getSharedPreferences(
            "login", Context.MODE_PRIVATE)

        binding.email.setText("test@gmail.com")
        binding.password.setText("password")

        //Login button
        binding.login.setOnClickListener {
            val userEmail = binding.email.text.toString().replace(" ","")
            val userPassword = binding.password.text.toString().replace(" ","")

            val validate = todoViewModel.loginValidation(binding.root,
                userEmail,
                userPassword,
                binding.wrongEmail,binding.wrongPassword,binding.email,binding.password,this)

            if (validate){
                sharedPref.edit().putBoolean("logged", true).apply()
                //move to the dashBoard
                val intent = Intent(applicationContext,MainActivity::class.java)
                startActivity(intent)
                finish()

            }

        }

        val biometricManager = BiometricManager.from(this)
        when (biometricManager.canAuthenticate()) {
            BiometricManager.BIOMETRIC_SUCCESS ->
                Log.d("MY_APP_TAG", "App can authenticate using biometrics.")
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE ->
                Log.e("MY_APP_TAG", "No biometric features available on this device.")
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE ->
                Log.e("MY_APP_TAG", "Biometric features are currently unavailable.")
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED ->
                Log.e("MY_APP_TAG", "The user hasn't associated " +
                        "any biometric credentials with their account.")
        }



    }



}
