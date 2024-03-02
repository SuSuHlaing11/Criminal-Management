import javafx.scene.control.Control;
import javafx.scene.control.TableCell;
import javafx.scene.text.Text;

public class WrappingTextTableCell<T> extends TableCell<T, String> {
    private final Text text;

    private static final double PADDING = 10.0;

    public WrappingTextTableCell() {
        text = new Text();
        setGraphic(text);
        setPrefHeight(Control.USE_COMPUTED_SIZE);
        text.wrappingWidthProperty().bind(widthProperty().subtract(PADDING));
        text.setSmooth(true);
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (item == null || empty) {
            text.setText(null);
            setGraphic(null);
        } else {
            text.setText(item);
            setGraphic(text);
        }
    }
}
