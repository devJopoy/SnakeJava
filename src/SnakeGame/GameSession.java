package SnakeGame;

import java.util.*;

// appeared on the mid body of snake

public class GameSession {
    private HashSet<PixelBlock> pixelBlockList;
    private PixelBlock[][] mapGrid;
    private Snake snake;
    private int direction;
    private Queue<Integer> directionList;
    private boolean isSnakeDirectionUpdate;
    private Random foodGenerator;
    private boolean isSessionStarted;
    private int score;

    // UI
    private GameUI gameUI;
    protected final static int MAP_SIZE_X = 200;
    protected final static int MAP_SIZE_Y = 200;
    
    public static void main(String[] args){
        GameSession gameSession = new GameSession();
        gameSession.startSession();
    }

    public GameSession(){
        // init components
        pixelBlockList= new HashSet<PixelBlock>();
        mapGrid = new PixelBlock[MAP_SIZE_X / 20][MAP_SIZE_Y / 20];
        foodGenerator = new Random();
        isSessionStarted = false;
        score = 0;

        // init snake
        snake = new Snake(this, 3);
        direction = 3;
        isSnakeDirectionUpdate = true;
        directionList = new LinkedList<Integer>();

        // init UI
        gameUI = new GameUI(this);

    }

    public void startSession(){
        generateFood();
        snake.run();

    }

    public void setDirection(int direction){
        if(((this.direction == 3) && (direction == 5)) || ((this.direction == 5) && (direction == 3))
        || ((this.direction == 4) && (direction == 6)) || ((this.direction == 6) && (direction == 4))){
            return;
        }
        directionList.add(direction);
        isSnakeDirectionUpdate = false;
        this.direction = direction;
    }

    public void setFinal(){
        gameUI.updateMap();
    }

    public void setBlock(int x, int y, boolean isSet){
        // boundary 0 < x < MAP_SIZE_X
        // boundary 0 < y < MAP_SIZE_Y
        if(x >= (MAP_SIZE_X/20) || x < 0 || y >= (MAP_SIZE_Y/20) || y < 0){
            System.out.println("Game OVER");
            while(true);
        }

        if(isSet){
            // check if food is in this pixel block
            if(mapGrid[x][y] != null){
                // if food then feed 
                // if snake body then game over
                if(mapGrid[x][y].getIsFood()){
                    snake.feed();
                    mapGrid[x][y].setBlock();
                    generateFood();
                    score += 50;
                    gameUI.updateScore("" + score);
                }else{
                    System.out.println("GAME OVER");
                    while(true);
                }

            }else{
                PixelBlock b = new PixelBlock(x, y, false);
                mapGrid[x][y] = b;
                pixelBlockList.add(b);
            }

        }else{
            pixelBlockList.remove(mapGrid[x][y]);
            mapGrid[x][y] = null;
        }
    }

    public int getDirection(){
        isSnakeDirectionUpdate = true;
        if(directionList.isEmpty()){
            return this.direction;
        }else{
            return directionList.remove();
        }
    }

    public Iterator<PixelBlock> getPixelBlockIterator(){
        return pixelBlockList.iterator();
    }

    private void generateFood(){
        int x = 0;
        int y = 0;
        if(isSessionStarted){
            do{
                x = foodGenerator.nextInt(MAP_SIZE_X / 20);
                y = foodGenerator.nextInt(MAP_SIZE_Y / 20);
                //System.out.println("Created food");
                //if(x % 2 == 0){
                //    x = 0;
                //    y = 0;
                //}
            }while(mapGrid[x][y] != null);
        }else{
            x = 5 + foodGenerator.nextInt(MAP_SIZE_X / 100);
            y = 5 + foodGenerator.nextInt(MAP_SIZE_Y / 100);
            isSessionStarted = true;
            //System.out.println("Created food");
        }


        PixelBlock b = new PixelBlock(x, y, true);
        mapGrid[x][y] = b;
        pixelBlockList.add(b);
    }

    protected class PixelBlock{
        private int x;
        private int y;
        private boolean isFood;

        public PixelBlock(int x, int y, boolean isFood){
            this.x = x;
            this.y = y;
            this.isFood = isFood;
        }

        public int getX(){
            return this.x;
        }

        public int getY(){
            return this.y;
        }

        public boolean getIsFood(){
            return isFood;
        }

        public void setBlock(){
            isFood = false;
        }

        @Override
		public int hashCode() {
			return 100 * x + y;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
                PixelBlock other = (PixelBlock) obj;
			if (this.x != other.x)
				return false;
			if (this.y != other.y)
				return false;
            if(this.isFood != other.isFood)
                return false;
			return true;
		}
    
    }
}
