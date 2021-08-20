package com.wakaztahir.parsefield

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wakaztahir.blockly.components.blocks.textblock.TextComponent
import com.wakaztahir.blockly.components.list.ListComponent
import com.wakaztahir.blockly.model.ListItem
import com.wakaztahir.parsefield.ui.theme.ParseFieldTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ParseFieldTheme {
                // rendering a list block
                val listBlock = remember {
                    com.wakaztahir.blockly.model.ListBlock().apply {
                        items.add(ListItem().apply { text = "First Item" })
                        items.add(ListItem().apply { text = "Second Item" })
                        items.add(ListItem().apply { text = "Third Item" })
                        items.add(ListItem().apply { text = "Fourth Item" })
                        items.add(ListItem().apply { text = "Fifth Item" })
                    }
                }
                Column {
                    TextComponent(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp))
                    ListComponent(
                        block = listBlock,
                        onUpdate = { /*TODO*/ },
                        onRemove = { /*TODO*/ }
                    )
                }
            }
        }
    }
}