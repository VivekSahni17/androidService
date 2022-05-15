package com.example.test1.fragment


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.example.test1.*

import com.example.test1.data.Employee
import android.content.Context.MODE_PRIVATE
import com.example.test1.activity.EmployeeDetailsActivity


class SignupFragment : Fragment() {
    lateinit var tvsignin: TextView
    lateinit var signup: Button
    lateinit var email: EditText
    lateinit var name: EditText
    lateinit var empId: EditText
    lateinit var password: EditText
    lateinit var confirmpassword: EditText
    private var passwordLength = 6


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_signup, container, false)
        tvsignin = view.findViewById(R.id.tvSignin)
        signup = view.findViewById(R.id.btnSignup)
        email = view.findViewById(R.id.etemail)
        empId = view.findViewById(R.id.etEmpId)
        password = view.findViewById(R.id.etPassword)
        name = view.findViewById(R.id.etName)
        confirmpassword = view.findViewById(R.id.etConfirmPassword)
        viewInitialize()
        signup.setOnClickListener {
            if (valid()){
            val emp = Employee(0, empId.text.toString(), email.text.toString(), name.text.toString(), password.text.toString())
//                val emailText =  email.text.toString()
//                val passwordText =  password.text.toString()
//                val tvname =  name.text.toString()
                val db = DatabaseHelper(requireContext())

//                val sharePref =  SharePreference(requireContext())
//                val args = Bundle()
//                args.putString("Email", emailText)
//                args.putString("Password", passwordText)
//                args.putString("Name",tvname)
//                sharePref.saveEmpData(emailText)

                if (db.checkEmpEmail(email.text.toString())) {
                email.error = "Email Already Exits"
                Toast.makeText(requireContext(), "Email Already Exits ", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            db.insertUser(emp)
            Toast.makeText(requireActivity(), "SignUp Success", Toast.LENGTH_SHORT).show()
                val intent = Intent(activity, EmployeeDetailsActivity::class.java)
                activity?.startActivity(intent)
                activity?.finish()
            }
        }
        tvsignin.setOnClickListener {
            Toast.makeText(requireActivity(), "Sign-In", Toast.LENGTH_SHORT).show()
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.Fragment_Container, LoginFragment())?.commit()
        }
        return view
    }


    private fun viewInitialize() {
        email.text.toString()
        password.text.toString()
        confirmpassword.text.toString()
        empId.text.toString()
        name.text.toString()
    }

    private fun valid(): Boolean {
        if (empId.text.toString() == "") {
            empId.error = "Please enter Employee ID"
            return false
        }
        if (email.text.toString() == ""){
            email.error = "Please enter Email ID"
        return false
    }
        if (!isEmailValid(email.text.toString())){
            email.error = "Please enter Kitlabs emailid"
            return false
        }
        if (name.text.toString()=="") {
            name.error = "Please enter Name"
            return false
        }
        if (password.text.toString() == ""){
            password.error = "Please enter password"
        }
        if (confirmpassword.text.toString() == "") {
            confirmpassword.error = "Please enter confirmpassword"
            return false
        }
        if (password.text.length<passwordLength){
            password.error=("Password Length Must be More then"+passwordLength+"Characters")
            return false
        }
        if (!password.text.toString().equals(confirmpassword.text.toString())){
            confirmpassword.error=("Password Does Not Match")
            return false

        }
        return true
    }


    private fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()

}
}














//val empActivity = EmployeeDetailsActivity()
//                editor?.putString("email", emailText);
//                editor?.putString("password", passwordText);
//                editor?.apply()


//                val sharePref =  SharePreference(requireContext())
//                val bundle =  Bundle()
//                bundle.putString("Email",emailText)
//                bundle.putString("Password", passwordText)
//                empActivity.arguments =  bundle
//                sharePref.saveEmpData(email.toString())
//                val editor = sharedPreference(requireContext())
//                editor?.putString("Email",emailText)
//                editor?.putString("Password",passwordText)
//                editor?.save




























