package com.gd.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import coil.compose.rememberImagePainter
import com.gd.myapplication.ui.theme.MyApplicationTheme
import com.google.android.material.chip.Chip
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
            }
        }
    }
}

@Composable
fun TwoTexts(modifier: Modifier = Modifier, text1: String, text2: String) {
    Row(modifier = modifier.height(IntrinsicSize.Min)) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 4.dp)
                .wrapContentWidth(Alignment.Start),
            text = text1
        )

        Divider(color = Color.Black, modifier = Modifier
            .fillMaxHeight()
            .width(1.dp))
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(end = 4.dp)
                .wrapContentWidth(Alignment.End),
            text = text2
        )
    }
}

@Preview
@Composable
fun TwoTextsPreview() {
    MyApplicationTheme {
        Surface {
            TwoTexts(text1 = "Hi", text2 = "there")
        }
    }
}


//@Composable
//fun ConstraintLayoutContent() {
////    ConstraintLayout {
////        val (button, text) = createRefs()
////
////        Button(
////            onClick = { /*TODO*/ },
////            modifier = Modifier.constrainAs(button) {
////                top.linkTo(parent.top, margin = 16.dp)
////            }
////        ) {
////            Text(text = "Button")
////        }
////
////        Text(text = "Text", Modifier.constrainAs(text) {
////            top.linkTo(button.bottom, margin = 16.dp)
////            centerHorizontallyTo(parent)
////        })
////    }
//
//    ConstraintLayout {
//        val (buttonFirst, buttonSecond, text) = createRefs()
//
//        Button(onClick = { /*TODO*/ }, modifier = Modifier.constrainAs(buttonFirst) {
//            top.linkTo(parent.top, margin = 16.dp)
//        }) {
//            Text(text = "Button First")
//        }
//
//        Text(text = "Text", modifier = Modifier.constrainAs(text) {
//            top.linkTo(buttonFirst.bottom, margin = 16.dp)
//            centerAround(buttonFirst.end)
//        })
//
//        val barrier = createEndBarrier(buttonFirst, text)
//
//        Button(onClick = { /*TODO*/ },
//            modifier = Modifier.constrainAs(buttonSecond) {
//                top.linkTo(parent.top, margin = 16.dp)
//                start.linkTo(barrier)
//            }
//        ) {
//            Text(text = "ButtonSecond", style = TextStyle(color = Color.Cyan))
//        }
//    }
//}

//@Composable
//fun LargeConstraintLayout() {
//    ConstraintLayout {
//        val text = createRef()
//
//        val guideline = createGuidelineFromStart(0.5f)
//        Text(
//            "This is a very very very very very very very long text",
//            Modifier.constrainAs(text) {
//                linkTo(guideline, parent.end)
//                width = Dimension.preferredWrapContent
//            }
//        )
//    }
//}

//@Preview
//@Composable
//fun LargeConstraintLayoutPreview() {
//    MyApplicationTheme {
//        LargeConstraintLayout()
//    }
//}


//@Preview
//@Composable
//fun ConstraintLayoutContentPreview() {
//    MyApplicationTheme {
//        ConstraintLayoutContent()
//    }
//}

@Preview
@Composable
fun DecoupledConstraintLayoutPreview() {
    MyApplicationTheme {
        DecoupledConstraintLayout()
    }
}

@Composable
fun DecoupledConstraintLayout() {
    BoxWithConstraints {
        val constraints = if (maxWidth < maxHeight) {
            decoupledConstraints(margin = 16.dp) // Portrait constraints
        } else {
            decoupledConstraints(margin = 32.dp) // Landscape constraints
        }

        ConstraintLayout(constraints) {
            Button(
                onClick = { /* Do something */ },
                modifier = Modifier.layoutId("button")
            ) {
                Text("Button")
            }

            Text("Text", Modifier.layoutId("text"))
        }
    }
}

private fun decoupledConstraints(margin: Dp): ConstraintSet {
    return ConstraintSet {
        val button = createRefFor("button")
        val text = createRefFor("text")

        constrain(button) {
            top.linkTo(parent.top, margin = margin)
        }
        constrain(text) {
            top.linkTo(button.bottom, margin)
        }
    }
}

//@Composable
//fun ImageListItem(index: Int) {
//    Row(verticalAlignment = Alignment.CenterVertically) {
//        Image(
//            painter = rememberImagePainter(
//                data = "https://developer.android.com/images/brand/Android_Robot.png"
//            ),
//            contentDescription = "Android Logo",
//            modifier = Modifier.size(50.dp)
//        )
//        Spacer(Modifier.width(10.dp))
//        Text("Item #$index", style = MaterialTheme.typography.subtitle1)
//    }
//}

//@Composable
//fun ScrollingList() {
//    val listSize = 100
//    // We save the scrolling position with this state
//    val scrollState = rememberLazyListState()
//    // We save the coroutine scope where our animated scroll will be executed
//    val coroutineScope = rememberCoroutineScope()
//
//    Column {
//        Row {
//            Button(onClick = {
//                coroutineScope.launch {
//                    // 0 is the first item index
//                    scrollState.animateScrollToItem(0)
//                }
//            }) {
//                Text("최상단")
//            }
//
//            Button(onClick = {
//                coroutineScope.launch {
//                    // listSize - 1 is the last index of the list
//                    scrollState.animateScrollToItem(listSize - 1)
//                }
//            }) {
//                Text("최하단")
//            }
//        }
//
//        LazyColumn(state = scrollState) {
//            items(listSize) {
//                ImageListItem(it)
//            }
//        }
//    }
//}

//@Composable
//fun ScrollingListPreview() {
//    MyApplicationTheme {
//        ScrollingList()
//    }
//}

//@Composable
//fun MyOwnColumn(
//    modifier: Modifier = Modifier,
//    content: @Composable () -> Unit
//) {
//    Layout(
//        modifier = modifier,
//        content = content
//    ) { measurables, constraints ->
//        val placeables = measurables.map { measurable ->
//            measurable.measure(constraints)
//        }
//        var yPosition = 0
//        layout(constraints.maxWidth, constraints.maxHeight) {
//            placeables.forEach { placeable ->
//                placeable.placeRelative(x = 0, y = yPosition)
//                yPosition += placeable.height
//            }
//        }
//    }
//}

//@Composable
//fun StaggeredGrid(
//    modifier: Modifier = Modifier,
//    rows: Int = 3,
//    content: @Composable () -> Unit
//) {
//    Layout(
//        modifier = modifier,
//        content = content
//    ) { measurables, constraints ->
//        val rowWidths = IntArray(rows) { 0 }
//        val rowHeights = IntArray(rows) { 0 }
//
//        val placeables = measurables.mapIndexed { index, measurable ->
//            val placeable = measurable.measure(constraints)
//            val row = index % rows
//            rowWidths[row] += placeable.width
//            rowHeights[row] = Math.max(rowHeights[row], placeable.height)
//            placeable
//        }
//
//        val width = rowWidths.maxOrNull()
//            ?.coerceIn(constraints.minWidth.rangeTo(constraints.maxWidth)) ?: constraints.minWidth
//
//        val height = rowHeights.sumOf { it }
//            .coerceIn(constraints.minHeight.rangeTo(constraints.maxHeight))
//
//        val rowY = IntArray(rows) { 0 }
//        for (i in 1 until rows) {
//            rowY[i] = rowY[i - 1] + rowHeights[i - 1]
//        }
//        layout(width, height) {
//            val rowX = IntArray(rows) { 0 }
//
//            placeables.forEachIndexed { index, placeable ->
//                val row = index % rows
//                placeable.placeRelative(
//                    x = rowX[row],
//                    y = rowY[row]
//                )
//                rowX[row] += placeable.width
//            }
//        }
//    }
//}

//@Composable
//fun Chip(modifier: Modifier = Modifier, text: String) {
//    Card(
//        modifier = modifier,
//        border = BorderStroke(color = Color.Black, width = Dp.Hairline),
//        shape = RoundedCornerShape(8.dp)
//    ) {
//        Row(
//            modifier = Modifier.padding(start = 8.dp, top = 4.dp, end = 8.dp, bottom = 4.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Box(
//                modifier = Modifier
//                    .size(16.dp, 16.dp)
//                    .background(color = MaterialTheme.colors.secondary)
//            )
//            Spacer(modifier = Modifier.width(4.dp))
//            Text(text = text)
//        }
//    }
//}

//@Preview
//@Composable
//fun ChipPreview() {
//    MyApplicationTheme() {
//        Chip(text = "Hi there!!!")
//    }
//}

//val topics = listOf(
//    "Arts & Crafts", "Beauty", "Books", "Business", "Comics", "Culinary",
//    "Design", "Fashion", "Film", "History", "Maths", "Music", "People", "Philosophy",
//    "Religion", "Social sciences", "Technology", "TV", "Writing"
//)

//@Composable
//fun BodyContent(modifier: Modifier = Modifier) {
//    Row(
//        modifier = modifier
//            .background(color = Color.LightGray, shape = RectangleShape)
//            .padding(16.dp)
//            .size(200.dp)
//            .horizontalScroll(rememberScrollState())
//    ) {
//        StaggeredGrid {
//            for (topic in topics) {
//                Chip(modifier = Modifier.padding(8.dp), text = topic)
//            }
//        }
//    }
//}

//@Preview
//@Composable
//fun BodyContentPreview() {
//    MyApplicationTheme {
//        BodyContent()
//    }
//}

//@Stable
//fun Modifier.padding(all: Dp) =
//    this.then(
//        PaddingModifier(start = all, top = all, end = all, bottom = all, rtlAware = true)
//    )

//private class PaddingModifier(
//    val start: Dp = 0.dp,
//    val top: Dp = 0.dp,
//    val end: Dp = 0.dp,
//    val bottom: Dp = 0.dp,
//    val rtlAware: Boolean,
//) : LayoutModifier {
//
//    override fun MeasureScope.measure(
//        measurable: Measurable,
//        constraints: Constraints
//    ): MeasureResult {
//
//        val horizontal = start.roundToPx() + end.roundToPx()
//        val vertical = top.roundToPx() + bottom.roundToPx()
//
//        val placeable = measurable.measure(constraints.offset(-horizontal, -vertical))
//
//        val width = constraints.constrainWidth(placeable.width + horizontal)
//        val height = constraints.constrainHeight(placeable.height + vertical)
//        return layout(width, height) {
//            if (rtlAware) {
//                placeable.placeRelative(start.roundToPx(), top.roundToPx())
//            } else {
//                placeable.place(start.roundToPx(), top.roundToPx())
//            }
//        }
//    }
//}


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