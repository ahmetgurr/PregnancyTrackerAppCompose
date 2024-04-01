import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import com.ahmetgur.pregnancytracker.MainActivity
import com.ahmetgur.pregnancytracker.viewmodel.AuthViewModel

@Composable
fun performLogoutAndNavigateToMain(context: Context, navController: NavController, authViewModel: AuthViewModel) {
    // AuthViewModel'den logout işlemini çağır
    authViewModel.logout()

    // MainActivity'yi başlatma ve mevcut aktiviteyi sonlandırma
    val intent = Intent(context, MainActivity::class.java)
    startActivity(context, intent, null)
    (context as Activity).finish()
}
