package com.hasoftware.ustore.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hasoftware.ustore.ui.screens.*

sealed class Screen(val route: String) {
    // Splash screen
    object Splash : Screen("splash")
    
    // Auth screens
    object Start : Screen("start")
    object Signup : Screen("signup")
    object Login : Screen("login")
    object ForgotPassword : Screen("forgot_password")
    
    // Main app screens
    object Home : Screen("home")
    object Shop : Screen("shop")
    object Search : Screen("search")
    object Wishlist : Screen("wishlist")
    object Cart : Screen("cart")
    object Profile : Screen("profile")
    object Categories : Screen("categories")
    
    // Settings screens
    object Settings : Screen("settings")
    object SettingsProfile : Screen("settings_profile")
    object ShippingAddress : Screen("shipping_address")
    object EditShippingAddress : Screen("edit_shipping_address")
    object LanguageSelection : Screen("language_selection")
    object CurrencySelection : Screen("currency_selection")
    
    // Product screens
    object ProductDetail : Screen("product_detail/{productId}") {
        fun createRoute(productId: String) = "product_detail/$productId"
    }
    object ProductVariations : Screen("product_variations/{productId}") {
        fun createRoute(productId: String) = "product_variations/$productId"
    }
    
    // Cart screens
    object EmptyCart : Screen("empty_cart")
    object Payment : Screen("payment")
}

@Composable
fun AppNavigation(
    navController: NavHostController,
    onLanguageChange: () -> Unit = {}
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        // Splash screen
        composable(Screen.Splash.route) {
            SplashScreen(
                onSplashFinished = {
                    navController.navigate(Screen.Start.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }
        
        // Auth screens
        composable(Screen.Start.route) {
            StartScreen(
                onGetStartedClick = {
                    navController.navigate(Screen.Signup.route)
                },
                onAlreadyHaveAccountClick = {
                    navController.navigate(Screen.Login.route)
                }
            )
        }
        
        composable(Screen.Signup.route) {
            SignupScreen(navController = navController)
        }
        
        composable(Screen.Login.route) {
            LoginScreen(navController = navController)
        }
        
        composable(Screen.ForgotPassword.route) {
            ForgotPasswordScreen(
                onSendResetLink = { email ->
                    // TODO: Handle forgot password
                },
                onBackClick = {
                    navController.popBackStack()
                },
                onMaxAttemptsReached = {
                    // TODO: Handle max attempts
                }
            )
        }
        
        
        // Main app screens
        composable(Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        
        composable(Screen.Shop.route) {
            ShopScreen(
                onNavigate = { route ->
                    when (route) {
                        "search" -> navController.navigate(Screen.Search.route)
                        "wishlist" -> navController.navigate(Screen.Wishlist.route)
                        "cart" -> navController.navigate(Screen.Cart.route)
                        "profile" -> navController.navigate(Screen.Profile.route)
                        "categories" -> navController.navigate(Screen.Categories.route)
                    }
                },
                currentRoute = "shop"
            )
        }
        
        composable(Screen.Search.route) {
            SearchScreen(
                onNavigate = { route ->
                    when (route) {
                        "shop" -> navController.navigate(Screen.Shop.route)
                        "cart" -> navController.navigate(Screen.Cart.route)
                        "profile" -> navController.navigate(Screen.Profile.route)
                    }
                },
                onProductClick = { productId ->
                    navController.navigate(Screen.ProductDetail.createRoute(productId))
                },
                currentRoute = "search"
            )
        }
        
        composable(Screen.Cart.route) {
            CartScreen(
                onNavigate = { route ->
                    when (route) {
                        "shop" -> navController.navigate(Screen.Shop.route)
                        "search" -> navController.navigate(Screen.Search.route)
                        "cart" -> navController.navigate(Screen.Cart.route)
                        "profile" -> navController.navigate(Screen.Settings.route)
                    }
                },
                onCheckoutClick = {
                    navController.navigate(Screen.Payment.route)
                },
                onEditAddressClick = {
                    navController.navigate(Screen.EditShippingAddress.route)
                },
                onItemQuantityChange = { itemId, newQuantity ->
                    // Handle quantity change
                },
                onRemoveItem = { itemId ->
                    // Handle item removal - navigate to empty cart if no items left
                    navController.navigate(Screen.EmptyCart.route)
                },
                currentRoute = "cart"
            )
        }
        
        composable(Screen.Wishlist.route) {
            WishlistScreen(
                onNavigate = { route ->
                    when (route) {
                        "shop" -> navController.navigate(Screen.Shop.route)
                        "search" -> navController.navigate(Screen.Search.route)
                        "cart" -> navController.navigate(Screen.Cart.route)
                        "profile" -> navController.navigate(Screen.Profile.route)
                    }
                },
                onProductClick = { productId ->
                    navController.navigate(Screen.ProductDetail.createRoute(productId))
                },
                currentRoute = "wishlist"
            )
        }
        
        composable(Screen.Profile.route) {
            // Navigate to Settings when Profile is clicked
            navController.navigate(Screen.Settings.route)
        }
        
        composable(Screen.Categories.route) {
            CategoriesScreen(
                onNavigate = { route ->
                    when (route) {
                        "shop" -> navController.navigate(Screen.Shop.route)
                        "search" -> navController.navigate(Screen.Search.route)
                        "cart" -> navController.navigate(Screen.Cart.route)
                        "profile" -> navController.navigate(Screen.Profile.route)
                    }
                },
                onCategoryClick = { categoryId ->
                    // Handle category click - could navigate to category products
                    navController.popBackStack()
                },
                currentRoute = "categories"
            )
        }
        
        // Product screens
        composable(Screen.ProductDetail.route) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: "1"
            ProductDetailScreen(
                productId = productId,
                onNavigate = { route ->
                    when (route) {
                        "shop" -> navController.navigate(Screen.Shop.route)
                        "search" -> navController.navigate(Screen.Search.route)
                        "cart" -> navController.navigate(Screen.Cart.route)
                        "profile" -> navController.navigate(Screen.Profile.route)
                    }
                },
                onBackClick = {
                    navController.popBackStack()
                },
                currentRoute = "product_detail"
            )
        }
        
        composable(Screen.ProductVariations.route) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: "1"
            ProductVariationsScreen(
                productId = productId,
                onAddToCart = {
                    // Handle add to cart
                    navController.popBackStack()
                },
                onBuyNow = {
                    // Handle buy now
                    navController.popBackStack()
                }
            )
        }
        
        // Settings screens
        composable(Screen.Settings.route) {
            SettingsScreen(
                onNavigate = { route ->
                    when (route) {
                        "shop" -> navController.navigate(Screen.Shop.route)
                        "search" -> navController.navigate(Screen.Search.route)
                        "cart" -> navController.navigate(Screen.Cart.route)
                        "profile" -> { /* Already in settings */ }
                    }
                },
                onProfileClick = {
                    navController.navigate(Screen.SettingsProfile.route)
                },
                onShippingAddressClick = {
                    navController.navigate(Screen.ShippingAddress.route)
                },
                onCountryClick = {
                    // Handle country selection
                },
                onCurrencyClick = {
                    navController.navigate(Screen.CurrencySelection.route)
                },
                onLanguageClick = {
                    navController.navigate(Screen.LanguageSelection.route)
                },
                onSizesClick = {
                    // Handle sizes selection
                },
                onTermsClick = {
                    // Handle terms and conditions
                },
                currentRoute = "profile"
            )
        }
        
        composable(Screen.SettingsProfile.route) {
            SettingsProfileScreen(
                onNavigate = { route ->
                    when (route) {
                        "shop" -> navController.navigate(Screen.Shop.route)
                        "search" -> navController.navigate(Screen.Search.route)
                        "cart" -> navController.navigate(Screen.Cart.route)
                        "profile" -> navController.navigate(Screen.Settings.route)
                    }
                },
                onBackClick = {
                    navController.popBackStack()
                },
                onSaveChanges = {
                    // Handle save changes
                    navController.popBackStack()
                },
                currentRoute = "profile"
            )
        }
        
        composable(Screen.ShippingAddress.route) {
            ShippingAddressScreen(
                onNavigate = { route ->
                    when (route) {
                        "shop" -> navController.navigate(Screen.Shop.route)
                        "search" -> navController.navigate(Screen.Search.route)
                        "cart" -> navController.navigate(Screen.Cart.route)
                        "profile" -> navController.navigate(Screen.Settings.route)
                    }
                },
                onBackClick = {
                    navController.popBackStack()
                },
                onSaveChanges = {
                    // Handle save changes
                    navController.popBackStack()
                },
                onEditAddress = {
                    navController.navigate(Screen.EditShippingAddress.route)
                },
                currentRoute = "profile"
            )
        }
        
        composable(Screen.EditShippingAddress.route) {
            EditShippingAddressScreen(
                onNavigate = { route ->
                    when (route) {
                        "shop" -> navController.navigate(Screen.Shop.route)
                        "search" -> navController.navigate(Screen.Search.route)
                        "cart" -> navController.navigate(Screen.Cart.route)
                        "profile" -> navController.navigate(Screen.Settings.route)
                    }
                },
                onBackClick = {
                    navController.popBackStack()
                },
                onSaveChanges = {
                    // Handle save changes
                    navController.popBackStack()
                },
                currentRoute = "profile"
            )
        }
        
        composable(Screen.LanguageSelection.route) {
            LanguageSelectionScreen(
                onNavigate = { route ->
                    when (route) {
                        "shop" -> navController.navigate(Screen.Shop.route)
                        "search" -> navController.navigate(Screen.Search.route)
                        "cart" -> navController.navigate(Screen.Cart.route)
                        "profile" -> navController.navigate(Screen.Settings.route)
                    }
                },
                onBackClick = {
                    navController.popBackStack()
                },
                onLanguageSelect = { languageId ->
                    // Handle language selection - recreate activity to apply new language
                    onLanguageChange()
                },
                currentRoute = "profile"
            )
        }
        
        composable(Screen.CurrencySelection.route) {
            CurrencySelectionScreen(
                onNavigate = { route ->
                    when (route) {
                        "shop" -> navController.navigate(Screen.Shop.route)
                        "search" -> navController.navigate(Screen.Search.route)
                        "cart" -> navController.navigate(Screen.Cart.route)
                        "profile" -> navController.navigate(Screen.Settings.route)
                    }
                },
                onBackClick = {
                    navController.popBackStack()
                },
                onCurrencySelect = { currencyId ->
                    // Handle currency selection
                    navController.popBackStack()
                },
                currentRoute = "profile"
            )
        }
        
        composable(Screen.EmptyCart.route) {
            EmptyCartScreen(
                onNavigate = { route ->
                    when (route) {
                        "shop" -> navController.navigate(Screen.Shop.route)
                        "search" -> navController.navigate(Screen.Search.route)
                        "cart" -> navController.navigate(Screen.Cart.route)
                        "profile" -> navController.navigate(Screen.Settings.route)
                    }
                },
                onCheckoutClick = {
                    // Checkout disabled for empty cart
                },
                onEditAddressClick = {
                    navController.navigate(Screen.EditShippingAddress.route)
                },
                currentRoute = "cart"
            )
        }
        
        composable(Screen.Payment.route) {
            PaymentScreen(
                onNavigate = { route ->
                    when (route) {
                        "shop" -> navController.navigate(Screen.Shop.route)
                        "search" -> navController.navigate(Screen.Search.route)
                        "cart" -> navController.navigate(Screen.Cart.route)
                        "profile" -> navController.navigate(Screen.Settings.route)
                    }
                },
                onPayClick = {
                    // Handle payment
                    navController.navigate(Screen.Shop.route)
                },
                onEditAddressClick = {
                    navController.navigate(Screen.EditShippingAddress.route)
                },
                onEditContactClick = {
                    // Handle edit contact info
                },
                onEditPaymentClick = {
                    // Handle edit payment method
                },
                currentRoute = "cart"
            )
        }
    }
}

