package fr.flowarg.flowupdaterjsoncreator.ui.components;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public interface ITakePlace
{
	default void setCanTakeAllSize(Node node)
	{
		 GridPane.setHgrow(node, Priority.ALWAYS);
	     GridPane.setVgrow(node, Priority.ALWAYS);
	}
}
