package main;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class SavePoint {
    public int uk1;
    public int lines;
    public int position;
    public int position_old;
    public int startPosition;
    public SavePoint(int uk1, int lines, int position, int position_old) {
        this.uk1 = uk1;
        this.lines = lines;
        this.position = position;
        this.position_old = position_old;
    }
    public SavePoint(int uk1, int lines, int position, int position_old, int startPosition) {
        this.uk1 = uk1;
        this.lines = lines;
        this.position = position;
        this.position_old = position_old;
        this.startPosition = startPosition;
    }

    public void print() {
        System.out.println("lines = " + (lines + 1));
        System.out.println("position = " + position);
        System.out.println("startPosition = " + startPosition);
    }
}
