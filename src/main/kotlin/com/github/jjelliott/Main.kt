package com.github.jjelliott

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.AwtWindow
import androidx.compose.ui.window.FrameWindowScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.github.jjelliott.components.episode.EpisodeTab
import com.github.jjelliott.components.map.MapTab
import java.awt.FileDialog
import java.io.File
import java.nio.file.Path


fun main() = application {
    Window(title = "MapDb Editor", onCloseRequest = ::exitApplication) {
        var path: String? by remember { mutableStateOf(null) }
        var file: JsonFile? by remember { mutableStateOf(null) }
        var tabIndex by remember { mutableStateOf(0) }
        var openingFile by remember { mutableStateOf(false) }

        val getFile = fun(): JsonFile? {
            return file
        }

        MaterialTheme {
            if (path == null) {
                Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
                    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                        if (openingFile) {
                            FileDialog(
                                title = "Test",
                                isLoad = true,
                                onResult = {
                                    if (it == null) {
                                        openingFile = false
                                    } else {
                                        path = it.toString()
                                        file = JsonFile(path!!)
                                    }
                                }
                            )
                        } else {
                            Button(onClick = {
                                openingFile = true
                            }) {
                                Text("Open File")
                            }
                        }
                    }
                }
            } else {

                Row{

                    Column {
                        Button(onClick = {
                            path = null;
                        }) {
                            Text("Open File")
                        }
                    }
                }
                Row(Modifier.absolutePadding(0.dp, 36.dp, 0.dp, 0.dp)){
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        mainPane({ tabIndex }, { i -> tabIndex = i }, getFile)
                    }
                }
            }
        }
    }
}

@Composable
fun mainPane(getTabIndex: () -> Int, setTabIndex: (Int) -> Unit, getFile: () -> JsonFile?) {

    TabRow(tabs = {
        Tab(onClick = {
            setTabIndex(0)
        }, content = {
            Text("Episodes")
        }, selected = false)
        Tab(onClick = {
            setTabIndex(1)
        }, content = {
            Text("Maps")
        }, selected = false)
    }, selectedTabIndex = getTabIndex())
        if (getTabIndex() == 0) {
            EpisodeTab(getFile)
        } else {
            MapTab(getFile)
        }
}

@Composable
fun VerticalScrollbar(
    modifier: Modifier,
    scrollState: ScrollState
) = androidx.compose.foundation.VerticalScrollbar(
    rememberScrollbarAdapter(scrollState),
    modifier
)


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun VerticalScrollbar(
    modifier: Modifier,
    scrollState: LazyListState,
    itemCount: Int,
    averageItemSize: Dp
) = androidx.compose.foundation.VerticalScrollbar(
    rememberScrollbarAdapter(scrollState, itemCount, averageItemSize),
    modifier
)

@Composable
fun FrameWindowScope.FileDialog(
    title: String,
    isLoad: Boolean,
    onResult: (result: Path?) -> Unit
) = AwtWindow(
    create = {
        object : FileDialog(window, "Choose a file", if (isLoad) LOAD else SAVE) {
            override fun setVisible(value: Boolean) {
                super.setVisible(value)
                if (value) {
                    if (file != null) {
                        onResult(File(directory).resolve(file).toPath())
                    } else {
                        onResult(null)
                    }
                }
            }
        }.apply {
            this.title = title
        }
    },
    dispose = FileDialog::dispose
)

