package chuco.joel.challengeibk.presentation

import android.app.AlertDialog
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import chuco.joel.challengeibk.R
import chuco.joel.challengeibk.databinding.ActivityMainBinding
import chuco.joel.challengeibk.presentation.util.ChallengeReceiver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var toolbar: Toolbar
    private val broadcast = ChallengeReceiver(::showMessageSessionExpired)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        toolbar = binding.topAppBar
        supportActionBar?.setDisplayShowTitleEnabled(false)
        setSupportActionBar(toolbar as Toolbar?)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        val appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        val intentFilter = IntentFilter("chuco.joel.challengeibk.SESSION_EXPIRED")
        registerReceiver(broadcast, intentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(broadcast)
    }

    private fun showMessageSessionExpired() {

        val alertDialog = AlertDialog.Builder(this)
            .setTitle("Sesión expirada")
            .setMessage("Tu sesión ha expirado. Por favor, vuelve a iniciar sesión.")
            .setPositiveButton("ACEPTAR") { dialog, _ ->
                dialog.dismiss()
                navController.popBackStack()
                navController.navigate(R.id.loginFragment)
                Log.i("*** showMessageSessionExpired: ", "popBackStack")
            }
            .setCancelable(false)
            .create()
        alertDialog.show()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}