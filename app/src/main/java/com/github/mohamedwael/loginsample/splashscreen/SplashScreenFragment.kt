package com.github.mohamedwael.loginsample.splashscreen

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.github.mohamedwael.loginsample.R


class SplashScreenFragment : Fragment() {

    companion object {
        fun newInstance() = SplashScreenFragment()
    }

    private lateinit var viewModel: SplashScreenViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(SplashScreenViewModel::class.java)

        return inflater.inflate(R.layout.splash_screen_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(SplashScreenViewModel::class.java)
        viewModel.navigateNext.observe(viewLifecycleOwner, Observer {
            if (it) Navigation.findNavController(view).navigate(
//                R.id.action_splashScreenFragment_to_code_login_nav_graph
                R.id.action_splashScreenFragment_to_password_login_nav_graph
            )
        })
        viewModel.startNavigation()
    }

}
