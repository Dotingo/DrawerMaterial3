package com.example.randomizer.presentation.screens.lists

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.randomizer.R
import com.example.randomizer.data.local.entities.ListEntity
import com.example.randomizer.presentation.components.lazyVerticalScrollbar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuListScreen(
    navigateToCreateListScreen: () -> Unit,
    navigateToList: (Int) -> Unit,
    paddingValues: PaddingValues,
    listViewModel: ListViewModel = hiltViewModel()
) {
    val focusManager = LocalFocusManager.current

    val query by listViewModel.query.collectAsStateWithLifecycle()
    val lists by listViewModel.getAllLists.collectAsStateWithLifecycle(initialValue = emptyList())

    val listsSearch by remember(lists, query) {
        derivedStateOf {
            if (query.isEmpty()) {
                lists
            } else {
                lists.filter { it.name.contains(query, ignoreCase = true) }
            }
        }
    }

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { navigateToCreateListScreen() },
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_add),
                        contentDescription = "create list button"
                    )
                },
                text = { Text(text = stringResource(id = R.string.create_list)) },
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = (paddingValues.calculateTopPadding().value - it.calculateTopPadding().value).dp)
        ) {
            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                colors = SearchBarDefaults.colors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
                inputField = {
                    SearchBarDefaults.InputField(
                        query = query,
                        onQueryChange = { query ->
                            listViewModel.updateQuery(query)
                        },
                        onSearch = { focusManager.clearFocus() },
                        placeholder = { Text(text = stringResource(R.string.search_bar_placeholder)) },
                        expanded = false,
                        onExpandedChange = {},
                        trailingIcon = {
                            if (query.isNotBlank()) {
                                IconButton(onClick = {
                                    listViewModel.updateQuery("")
                                }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_clear),
                                        contentDescription = "clear"
                                    )
                                }
                            }
                        },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_search),
                                contentDescription = "search"
                            )
                        }
                    )
                },
                expanded = false,
                onExpandedChange = {}
            ) {}
            val lazyListState = rememberLazyListState()
            LazyColumn(
                state = lazyListState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp)
                    .lazyVerticalScrollbar(lazyListState)
            ) {
                items(items = listsSearch, key = { item -> item.id!! }) { list ->
                    Lists(list, navigateToList, listViewModel.listFromJson(list.items))
                }
            }
        }
    }
}

@Composable
fun Lists(listEntity: ListEntity, navigateToList: (id: Int) -> Unit, listItems: List<String>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navigateToList(listEntity.id!!)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = listEntity.name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "${stringResource(R.string.items_count)} ${listItems.size}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(20.dp))
        }
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_right),
            contentDescription = null,
            modifier = Modifier.size(16.dp)
        )
    }
}

