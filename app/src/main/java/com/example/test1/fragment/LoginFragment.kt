package com.example.test1.fragment


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
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
import com.example.test1.DatabaseHelper
import com.example.test1.R
import com.example.test1.SharePreference
import com.example.test1.activity.EmployeeDetailsActivity


import android.content.SharedPreferences as SharedPreferences


class LoginFragment : Fragment() {
    lateinit var email:EditText
    lateinit var password:EditText
   // lateinit var name:EditText
    lateinit var btnsignin:Button
    lateinit var tvsignup:TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    @SuppressLint("CommitPrefEdits")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       val  view = inflater.inflate(R.layout.fragment_login, container, false)
        email = view.findViewById(R.id.edtemail)
        password = view.findViewById(R.id.edtPassword)
        btnsignin = view.findViewById(R.id.btnSignin)
        //name =  view.findViewById(R.id.tvname)
        tvsignup = view.findViewById(R.id.tvsignup)

        viewInitialize()
        btnsignin.setOnClickListener {
            if(valid()){
            val emailText = email.text.toString()
            val passwordText = password.text.toString()
                //val nameText =  name.text.toString()
               // val rf = EmployeeDetailsActivity()
                val sharePref =  SharePreference(requireContext())
                // args.putString("Name",nameText)
           //  rf.arguments = args
             sharePref.saveEmpData(emailText)
                val isValid = DatabaseHelper(requireContext())
               if (isValid.checkEmp(emailText,passwordText)){
                   Toast.makeText(requireActivity(),"Login Successful", Toast.LENGTH_SHORT).show()
                val intent = Intent(activity, EmployeeDetailsActivity::class.java)
                activity?.startActivity(intent)
                activity?.finish()
            }
            else
                Toast.makeText(requireActivity(),"Invalid Email Or Password", Toast.LENGTH_SHORT).show()


            }

        }

        tvsignup.setOnClickListener {
            Toast.makeText(requireActivity(),"Signup",Toast.LENGTH_SHORT).show()
            activity?.supportFragmentManager?.beginTransaction()?.replace(
                R.id.Fragment_Container,
                SignupFragment()
            )?.commit()
    }

        return view
    }


    private fun viewInitialize() {
       email.text.toString()
        password.text.toString()
    }




    private fun valid(): Boolean {
        if (email.text.toString() ==  ""){
            email.error = "Please enter email"
            return false
        }
        if (!isEmailValid(email.text.toString())) {
            email.error = "Wrong Email"
            return false
        }
        if (password.text.toString() == "") {
            Toast.makeText(requireActivity(),"Enter Password",Toast.LENGTH_SHORT).show()
            return false
        }
        return true

    }

    private fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
