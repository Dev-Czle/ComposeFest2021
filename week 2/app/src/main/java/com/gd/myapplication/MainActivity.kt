package com.gd.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.gd.myapplication.ui.theme.MyApplicationTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                ScrollingList()
            }
        }
    }
}

@Composable
fun ImageListItem(index: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = rememberImagePainter(
                data = "https://developer.android.com/images/brand/Android_Robot.png"
            ),
            contentDescription = "Android Logo",
            modifier = Modifier.size(50.dp)
        )
        Spacer(Modifier.width(10.dp))
        Text("Item #$index", style = MaterialTheme.typography.subtitle1)
    }
}

@Composable
fun ScrollingList() {
    val listSize = 100
    // We save the scrolling position with this state
    val scrollState = rememberLazyListState()
    // We save the coroutine scope where our animated scroll will be executed
    val coroutineScope = rememberCoroutineScope()

    Column {
        Row {
            Button(onClick = {
                coroutineScope.launch {
                    // 0 is the first item index
                    scrollState.animateScrollToItem(0)
                }
            }) {
                Text("최상단")
            }

            Button(onClick = {
                coroutineScope.launch {
                    // listSize - 1 is the last index of the list
                    scrollState.animateScrollToItem(listSize - 1)
                }
            }) {
                Text("최하단")
            }
        }

        LazyColumn(state = scrollState) {
            items(listSize) {
                ImageListItem(it)
            }
        }
    }
}

@Preview
@Composable
fun ScrollingListPreview(){
    MyApplicationTheme {
        ScrollingList()
    }
}

//@Preview
//@Composable
//fun ImageListPreview(){
//    MyApplicationTheme {
//        ImageList()
//    }
//}
//
//@Composable
//fun ImageList() {
//    val scrollState = rememberLazyListState()
//    LazyColumn(state = scrollState) {
//        items(100) { idx ->
//            ImageListItem(index = idx)
//        }
//    }
//}

//@Composable
//fun SimpleList() {
//    val scrollState = rememberScrollState()
//    Column(modifier = Modifier.verticalScroll(scrollState)) {
//        repeat(100) {
//            Text(text = "Item #$it")
//        }
//    }
//}
//
//@Preview
//@Composable
//fun SimpleListPreview() {
//    MyApplicationTheme {
//        SimpleList()
//    }
//}

//@Composable
//fun LayoutsCodelab() {
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = {
//                    Text(text = "LayoutsCodelab")
//                },
//                actions = {
//                    IconButton(onClick = { /* doSomething() */ }) {
//                        Icon(Icons.Filled.Favorite, contentDescription = null)
//                    }
//                })
//        }
//    ) { innerPadding ->
//        BodyContent(
//            Modifier
//                .padding(innerPadding)
//                .padding(8.dp)
//        )
//    }
//}

//@Composable
//fun BodyContent(modifier: Modifier = Modifier) {
//    Column(modifier = modifier.padding(8.dp)) {
//        Text(text = "Hi there!")
//        Text(text = "Thanks for going through the Layouts codelab")
//    }
//}
//
//@Preview
//@Composable
//fun LayoutsCodelabPreview() {
//    MyApplicationTheme {
//        LayoutsCodelab()
//    }
//}


//@Composable
//fun PhotographerCard() {
//    Row(
//        modifier = Modifier
//            .padding(8.dp)
//            .clip(RoundedCornerShape(4.dp))
//            .background(MaterialTheme.colors.surface)
//            .clickable { }
//            .padding(16.dp)
//    ) {
//        Surface(
//            modifier = Modifier.size(50.dp),
//            shape = CircleShape,
//            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
//        ) {
//
//        }
//        Column(
//            modifier = Modifier
//                .padding(start = 8.dp)
//                .align(Alignment.CenterVertically)
//        ) {
//            Text("Alfred Sisley", fontWeight = FontWeight.Bold)
//            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
//                Text("3 minutes ago", style = MaterialTheme.typography.body2)
//            }
//        }
//    }
//}
//
//@Preview
//@Composable
//fun PhotographerCardPreview() {
//    MyApplicationTheme {
//        PhotographerCard()
//    }
//}