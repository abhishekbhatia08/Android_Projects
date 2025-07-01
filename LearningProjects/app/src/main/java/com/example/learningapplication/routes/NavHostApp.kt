import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.learningapplication.components.DashboardScreen
import com.example.learningapplication.components.LoginUi
import com.example.learningapplication.routes.Routes

@Composable
fun NavHostApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN
    ) {
        composable(Routes.LOGIN) {
            LoginUi(navController)
        }

        composable(
            route = Routes.HOME,
            arguments = listOf(navArgument("userName") { type = NavType.StringType })
        ) { backStackEntry ->
            val userName = backStackEntry.arguments?.getString("userName") ?: ""
            DashboardScreen(navController, name = userName)
        }
    }
}