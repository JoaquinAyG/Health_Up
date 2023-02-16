package study.project.activities

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.ViewPager
import study.project.HealthUpApplication
import study.project.adapters.RegisterViewPageAdapter
import study.project.databinding.ActivityRegisterBinding
import study.project.factories.UserViewModelFactory
import study.project.fragments.register.*
import study.project.models.UserProfile
import study.project.utils.forceDarkMode
import study.project.viewmodels.RegisterViewModel
import study.project.viewmodels.UserViewModel

class RegisterActivity : FragmentActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val userViewModel: UserViewModel by viewModels {
        UserViewModelFactory((application as HealthUpApplication).userRepository)
    }
    private val registerViewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        forceDarkMode()
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)

        val fragments = listOf(
            //Insert here the fragments in order pls
            GenderFragment(),
            AgeFragment(),
            WeightFragment(),
            HeightFragment(),
            NameFragment(),
            MailFragment(),
            CapableDaysFragment(),
            CheckFragment(),
            PasswordFragment()
        )

        val adapter = RegisterViewPageAdapter(supportFragmentManager, fragments)

        binding.apply {
            vpRegister.adapter = adapter
            tabLayout.setupWithViewPager(vpRegister)

            btnNext.setOnClickListener {
                if (vpRegister.currentItem == 5) {
                    if (adapter.getFragment(vpRegister.currentItem).commitChanges() && !mailExists()) {
                        vpRegister.currentItem = vpRegister.currentItem + 1
                    } else {
                        AlertDialog.Builder(this@RegisterActivity)
                            .setTitle("Error")
                            .setMessage("The mail is already in use")
                            .setPositiveButton("Ok") { dialog, _ ->
                                dialog.dismiss()
                            }
                            .show()
                    }
                } else if (adapter.getFragment(vpRegister.currentItem).commitChanges()) {
                    vpRegister.currentItem = vpRegister.currentItem + 1
                }
            }

            progressBar.progress = 100 / vpRegister.adapter!!.count
            vpRegister.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                }

                override fun onPageSelected(position: Int) {
                    progressBar.progress = (position + 1) * 100 / vpRegister.adapter!!.count
                    btnConfirm.isVisible = position == vpRegister.adapter!!.count - 1
                    btnNext.isVisible = position != vpRegister.adapter!!.count - 1
                }

                override fun onPageScrollStateChanged(state: Int) {}
            })

            btnBack.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }
            btnConfirm.setOnClickListener {
                if (adapter.getFragment(vpRegister.currentItem).commitChanges()) {
                    registerViewModel.updateId((Math.random() * 1000000).toInt())
                    val user = registerViewModel.user.value!!
                    userViewModel.insert(user)
                    UserProfile.instance = user
                    Intent(this@RegisterActivity, MainActivity::class.java).apply {
                        startActivity(this)
                    }
                }
            }
        }
    }

    fun mailExists(): Boolean {
        var exists = false
        userViewModel.allUsers.observe(this) {
            it.forEach { user ->
                if (user.email == registerViewModel.user.value!!.email) {
                    exists = true
                }
            }
        }
        return exists
    }

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (binding.vpRegister.currentItem == 0) {
                finish()
            } else {
                binding.vpRegister.currentItem = binding.vpRegister.currentItem - 1
            }
        }
    }
}