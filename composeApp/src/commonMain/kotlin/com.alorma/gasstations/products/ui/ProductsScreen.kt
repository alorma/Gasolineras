package com.alorma.gasstations.products.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.alorma.gasstations.ui.UiState
import moe.tlaster.precompose.koin.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductsScreen(
  viewModel: ProductsListViewModel = koinViewModel(
    vmClass = ProductsListViewModel::class,
  ),
  onBack: () -> Unit,
) {
  val state by viewModel.state.collectAsState()

  val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
  Scaffold(
    modifier = Modifier.nestedScroll(
      connection = scrollBehavior.nestedScrollConnection
    ),
    topBar = {
      val currentState = state
      if (currentState is UiState.Success) {
        MediumTopAppBar(
          title = { Text(text = "Product types") },
          scrollBehavior = scrollBehavior,
          navigationIcon = {
            IconButton(onClick = onBack) {
              Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }
          },
        )
      }
    },
  ) { paddingValues ->
    LaunchedEffect(Unit) {
      viewModel.onInit()
    }

    Column(
      modifier = Modifier.padding(
        top = paddingValues.calculateTopPadding(),
      ),
    ) {
      when (val currentState = state) {
        UiState.Initial -> {}
        is UiState.Success -> {
          ProductsListContent(
            state = currentState.state,
            onSelected = { product ->
              viewModel.onProductSelected(product)
            },
          )
        }

        is UiState.Error -> {}
      }
    }
  }
}

@Composable
fun ProductsListContent(
  state: List<ProductUiModule>,
  onSelected: (product: ProductUiModule) -> Unit,
) {
  LazyColumn {
    items(state) { product ->
      ProductItemComponent(
        product = product,
        onSelected = onSelected,
      )
    }
  }
}

@Composable
fun ProductItemComponent(
  product: ProductUiModule,
  onSelected: (product: ProductUiModule) -> Unit,
) {
  Row(
    modifier = Modifier.fillMaxWidth()
      .clickable(role = Role.Checkbox) { onSelected(product) }
      .padding(horizontal = 16.dp, vertical = 8.dp),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(8.dp),
  ) {
    Checkbox(
      modifier = Modifier.minimumInteractiveComponentSize(),
      checked = product.selected,
      onCheckedChange = null,
    )
    Text(
      modifier = Modifier.weight(1f),
      text = product.name,
    )
    Text(
      text = "(${product.abbreviation})",
      style = MaterialTheme.typography.bodySmall,
      fontStyle = FontStyle.Italic,
      fontWeight = FontWeight.Bold,
    )
  }
}
