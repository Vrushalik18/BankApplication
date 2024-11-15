package com.cdb22dx005.loginwithretrofit.Login

import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.cdb22dx005.loginwithretrofit.*
import com.cdb22dx005.loginwithretrofit.database.LoginDatabase
import com.cdb22dx005.loginwithretrofit.databinding.FragmentLoginBinding
import com.cdb22dx005.roomdatabase.LoginEntity1


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        val view = binding.root

//
//        //***********Post Retrofit**********
//        val retrofitBuilder = Retrofit.Builder()
//            .addConverterFactory(GsonConverterFactory.create())
//            .baseUrl(BASE_URL)
//            .build()
//
//        val interFace = retrofitBuilder.create(LoginInterface::class.java)
//
//        val logData = LoginData("token", "status", "username")
//        val call = interFace.postData(logData)
//
//        call.enqueue(object : Callback<LoginData> {
//            override fun onResponse(call: Call<LoginData>, response: Response<LoginData>) {
//                val responseBody = response.body()!!
//
//            }
//
//            override fun onFailure(call: Call<LoginData>, t: Throwable) {
//
//            }
//        })

        binding.etUsername.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (!(Patterns.EMAIL_ADDRESS.matcher(binding.etUsername.text.toString())).matches()) {
                    binding.etUsername.setError("Invalid email")
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
        binding.etPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                val password = binding.etPassword.text.toString()
                if (!password.matches(".*[A-Z].*".toRegex())) {
                    binding.etPassword.error = "Minimum 1 Upper-Case Character Required"
                } else if (!password.matches(".*[a-z].*".toRegex())) {
                    binding.etPassword.error = "Minimum 1 Lower-Case Character Required"
                } else if (!password.matches(".*[0-9].*".toRegex())) {
                    binding.etPassword.error = "Minimum 1 Number Required"
                } else if (!password.matches(".*[!@#\$%^&*=-].*".toRegex())) {
                    binding.etPassword.error = "Minimum 1 Special Character Required"
                } else if (password.length < 8) {
                    binding.etPassword.error = "Password Must Contain More Than 8 Characters"
                }
            }
            override fun afterTextChanged(p0: Editable?) {
            }
        })
        binding.btnLogin.setOnClickListener {

            validate()
            Navigation.findNavController(view)
                .navigate(R.id.action_loginFragment_to_dashboardActivity)
        }
        return view
}
    private fun validate() {
        val uname = binding.etUsername.text.toString()
        val password = binding.etPassword.text.toString()

        val dao = LoginDatabase.getDatabase(requireContext()).loginDao()
        val loginRepository = LoginRepository(dao)

        loginViewModel = ViewModelProvider(
            this,
            LoginViewModelFactory(loginRepository)
        )[LoginViewModel::class.java]

        saveFeedback(uname,password)
    }
    private fun saveFeedback(userName: String, password : String) {
        val login = LoginEntity1(0, username = userName, password = password)
        loginViewModel.insertUser(login)

    }
}



