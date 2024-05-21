package com.kirara.sample.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kirara.core.ui.template.MainTemplate
import com.kirara.core.R
import com.kirara.core.data.UiState
import com.kirara.core.data.model.ProductResponse
import com.kirara.core.ui.component.molecules.SearchBar
import com.kirara.core.ui.theme.Gray200
import com.kirara.sample.components.ProgressProduct
import com.kirara.sample.home.HomeViewModel
import com.kirara.sample.search.section.SearchContent

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToDetail: (Int) -> Unit,
    navigateBack: () -> Unit
){
    val query by viewModel.query
    val focusRequester = remember { FocusRequester() }
    val uiStateProduct by remember { viewModel.uiStateProduct }.collectAsState()

    LaunchedEffect(Unit){
        focusRequester.requestFocus()
    }

    MainTemplate(
        modifier = modifier,
        topBar = {
            Row (
                modifier = modifier.background(MaterialTheme.colorScheme.primary),
                verticalAlignment = Alignment.CenterVertically
            ){
                IconButton(
                    onClick = navigateBack,
                    modifier = modifier.padding(start = 8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(id = R.string.back)
                    )
                }
                SearchBar(
                    query = query,
                    onQueryChange = viewModel::searchProductApiCall,
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.primary)
                        .focusRequester(focusRequester),
                )
            }
        },
        content = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = modifier
                    .fillMaxSize()
                    .background(Gray200)
            ){
                when(uiStateProduct){
                    is UiState.Loading -> {
                        viewModel.getProductsApiCall()
                        ProgressProduct()
                    }

                    is UiState.Success -> {
                        SearchContent(
                            modifier = modifier,
                            listProduct = (uiStateProduct as UiState.Success<ProductResponse>).data.products,
                            navigateToDetail = navigateToDetail
                        )
                    }

                    is UiState.Error -> {
                      Text(text = stringResource(id = R.string.error_product), color = MaterialTheme.colorScheme.onSurface)
                    }
                }
            }
        }
    )
}