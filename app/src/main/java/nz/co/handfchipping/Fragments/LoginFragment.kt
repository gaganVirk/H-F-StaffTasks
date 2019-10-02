package nz.co.handfchipping.Fragments

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import nz.co.handfchipping.Extensions.setOnTextChangedListener
import nz.co.handfchipping.Network.RequestManager
import nz.co.handfchipping.R
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import org.json.JSONObject


class LoginFragment : Fragment() {

    lateinit var buttonLogin: Button
    lateinit var editTextEmail: EditText
    lateinit var editTextPassword: EditText

    var isKeyboardShowing = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        buttonLogin = view.findViewById(R.id.buttonCCategory)
        editTextEmail = view.findViewById(R.id.editTextEmail)
        editTextPassword = view.findViewById(R.id.editTextCategory)

        buttonLogin.setOnClickListener { login() }
      //  editTextEmail.setOnTextChangedListener { textChanged() }
      //  editTextPassword.setOnTextChangedListener { textChanged() }

        val imageViewLogo = view.findViewById<ImageView>(R.id.imageViewLogo)

        view.addOnLayoutChangeListener { v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom ->

            Log.i("testing", "testing")

            val r = Rect()
            view.getWindowVisibleDisplayFrame(r);
            val screenHeight = view.rootView.height

            // r.bottom is the position above soft keypad or device button.
            // if keypad is shown, the r.bottom is smaller than that before.
            val keypadHeight = screenHeight - r.bottom

            Log.i("testing", keypadHeight.toString())

            Log.i("testing", (screenHeight * 0.15).toString())


            if (keypadHeight > screenHeight * 0.15) { // 0.15 ratio is perhaps enough to determine keypad height.
                // keyboard is opened
                if (!isKeyboardShowing) {
                    isKeyboardShowing = true

                    val lp = imageViewLogo.layoutParams

                    lp.height = 1

                    imageViewLogo.layoutParams = lp
                }
            } else {
                // keyboard is closed
                if (isKeyboardShowing) {
                    isKeyboardShowing = false

                    imageViewLogo.layoutParams = ConstraintLayout.LayoutParams(
                        ConstraintLayout.LayoutParams.WRAP_CONTENT,
                        ConstraintLayout.LayoutParams.WRAP_CONTENT
                    )
                }
            }
        }

        view.viewTreeObserver.addOnGlobalLayoutListener {

        }

        // Inflate the layout for this fragment
        return view
    }

    private fun login() {
        buttonLogin.isEnabled = false
        editTextEmail.isEnabled = false
        editTextPassword.isEnabled = false

        val email = editTextEmail.text.toString()
        val password = editTextPassword.text.toString()

        val body = "grant_type=password&userName=$email&password=$password"

        var request = Request.Builder().url("http://hfchipping.azurewebsites.net/api/Account/Token")
            .post(RequestBody.create("application/x-www-form-urlencoded".toMediaTypeOrNull(), body))
            .build()


        Thread(Runnable {
            val response =
                RequestManager.getInstance(requireContext()).okHttpClient.newCall(request).execute()

            handleLoginResponse(response)
        }).start()
    }

    private fun handleLoginResponse(response: Response) {
        if (!response.isSuccessful) {

            buttonLogin.isEnabled = true
            editTextEmail.isEnabled = true
            editTextPassword.isEnabled = true

            return
            // TODO show error
        }

        val responseBody = JSONObject(response.body?.string())

        val tokenString = responseBody.getString("access_token")

        val sharedPreferences =
            requireContext().getSharedPreferences("hfsystems", Context.MODE_PRIVATE).edit()

        sharedPreferences.putString("token", tokenString)

        sharedPreferences.apply()

        this.requireActivity().runOnUiThread {
            findNavController().navigate(R.id.action_loginFragment_to_nav_graph)
        }
    }
}