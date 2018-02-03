package engine;

public class gameContainer implements Runnable {

    private Thread thread;
    private Window window;

    private boolean running = false;
    private final double UPDATE_CAP = 1.0/60.0;
    private int width = 320, height = 240;
    private float scale = 4f;
    private String title = "engine v1.0";

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public gameContainer() {

    }

    public void start(){

        window = new Window(this);

        thread = new Thread(this);
        thread.run();
    }

    public void stop(){

    }

    public void run(){

        running = true;

        boolean render = false;
        double firstTime = 0;
        double lastTIme = System.nanoTime() / 1000000000.0;
        double passedTime = 0;
        double unprocessedTime = 0;

        double frameTime = 0;
        int frames = 0;
        int fps = 0;

        while(running){
            render = false;

            firstTime = System.nanoTime() / 1000000000.0;
            passedTime = firstTime - lastTIme;
            lastTIme = firstTime;

            unprocessedTime += passedTime;
            frameTime += passedTime;

            while(unprocessedTime >= UPDATE_CAP){
                unprocessedTime -= UPDATE_CAP;
                render = true;
                //TODO: Update game
                if(frameTime >= 1.0){
                    frameTime = 0;
                    fps = frames;
                    frames = 0;
                    System.out.println("FPS: " + fps);
                }
            }

            if(render){
                //TODO: render game
                window.update();
                frames ++;
            }else{
                try{
                    Thread.sleep(1);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
        dispose();
    }

    private void dispose(){

    }
    public static void main(String[] args) {
        gameContainer gc = new gameContainer();
        gc.start();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getTitle() {
        return title;
    }
}
