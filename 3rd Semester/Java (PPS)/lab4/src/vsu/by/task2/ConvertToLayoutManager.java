package vsu.by.task2;

import java.awt.*;

public class ConvertToLayoutManager {
    private final int IndexLayoutManager;
    public ConvertToLayoutManager(int IndexLayoutManager){
        this.IndexLayoutManager = IndexLayoutManager;
    }
    public LayoutManager GetLayoutManager(){
        FlowLayout current_layout = new FlowLayout();
        if (IndexLayoutManager == 0){
            current_layout.setAlignment((FlowLayout.LEFT));
            return current_layout;
        }
        else if (IndexLayoutManager == 1){
            current_layout.setAlignment((FlowLayout.RIGHT));
            return current_layout;
        }
        else if (IndexLayoutManager == 2){
            current_layout.setAlignment((FlowLayout.CENTER));
            return current_layout;
        }
        else if (IndexLayoutManager == 3){
            current_layout.setAlignment((FlowLayout.LEADING));
            return current_layout;
        }
        else if (IndexLayoutManager == 4){
            current_layout.setAlignment((FlowLayout.TRAILING));
            return current_layout;
        }
        return current_layout;
    }
}
