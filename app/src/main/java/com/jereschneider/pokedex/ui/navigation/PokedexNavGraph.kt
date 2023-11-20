package com.jereschneider.pokedex.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jereschneider.pokedex.domain.models.PokemonDetailModel
import com.jereschneider.pokedex.ui.navigation.utils.navigateTo
import com.jereschneider.pokedex.ui.navigation.utils.parcelable
import com.jereschneider.pokedex.ui.screens.details.DetailContent
import com.jereschneider.pokedex.ui.screens.home.HomeScreen
import com.jereschneider.pokedex.ui.screens.home.HomeViewModel

@Composable
fun PokedexNavGraph(
    navController: NavHostController,
    initialRoute: String = PokedexNavigation.Home.route,
    homeViewModel: HomeViewModel
) {
    NavHost(
        navController = navController,
        startDestination = initialRoute
    ) {
        composable(route = PokedexNavigation.Home.route) {
            HomeScreen(
                homeViewModel = homeViewModel
            ) { pokemonDetail ->
                navController.navigateTo(
                    route = PokedexNavigation.Detail.route,
                    value = pokemonDetail
                )
            }
        }
        composable(route = PokedexNavigation.Detail.route) {
            val detailPokemon = navController.parcelable<PokemonDetailModel>()
            detailPokemon?.let {
                DetailContent(
                    detailPokemon = detailPokemon,
                    onBackClick = { navController.popBackStack() },
                    onSubscribe = {}
                )
            }
        }
    }
}
