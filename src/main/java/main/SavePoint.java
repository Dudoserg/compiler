package main;

public class SavePoint {
    public int uk1;
    public int lines;
    public int position;
    public int position_old;
    public SavePoint(int uk1, int lines, int position, int position_old) {
        this.uk1 = uk1;
        this.lines = lines;
        this.position = position;
        this.position_old = position_old;
    }
}
