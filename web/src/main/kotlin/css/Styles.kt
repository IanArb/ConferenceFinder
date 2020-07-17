package css

import kotlinx.css.Display
import kotlinx.css.LinearDimension
import kotlinx.css.display
import kotlinx.css.marginLeft
import styled.StyleSheet

object ComponentStyles : StyleSheet("ComponentStyles", isStatic = true) {
    val paragraphWrapper by css {
        display = Display.inlineBlock
        marginLeft = LinearDimension("8px")
    }
}