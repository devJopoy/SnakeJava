package SnakeGame;

public class Snake{

    private GameSession gameSession;
    private int length;
    private int direction;
    private Node headNode;
    private Node tailNode;

    public Snake(GameSession gameSession, int length){
        this.gameSession = gameSession;
        this.length = length;
        this.direction = 3;
        Node parentNode = new Node();
        headNode = parentNode;
        gameSession.setBlock(0, 0, true);
        for(int i=1;i<length;i++){
            Node childNode = new Node();
            parentNode.setChildNode(childNode);
            parentNode = childNode;
        }

        tailNode = parentNode;
    }

    public void feed(){
        this.length++;
        Node childNode = new Node();
        tailNode.setChildNode(childNode);
        tailNode = childNode; 
    }

    public void displayInfo(){
        Node currentNode = headNode;
        currentNode.displayInfo();
        do{
            currentNode = currentNode.getChildNode();
            currentNode.displayInfo();
        } while(!currentNode.equals(tailNode));
    }

    public void run(){
        while(true){
            headNode.setLocationHead(gameSession.getDirection());
            try{
                Thread.sleep(40);
            }catch (Exception e){}
        }

    }

    private class Node{
        private int x;
        private int y;
        private Node childNode;

        public Node(){
            this.x = 0;
            this.y = 0;
            childNode = null;
            
        }

        public void setChildNode(Node childNode){
            this.childNode = childNode; 
            this.childNode.setLocation(this.x, this.y);
        }

        public Node getChildNode(){
            return this.childNode;
        }

        public void setLocationHead(int direction){
            if(direction == 3){
                setLocation(this.x + 1, this.y);
            }else if(direction == 4){
                setLocation(this.x, this.y + 1);
            }else if(direction == 5){
                setLocation(this.x - 1, this.y);
            }else if(direction == 6){
                setLocation(this.x, this.y - 1);
            }

            // update head grid to true
            gameSession.setBlock(this.x, this.y, true);
            gameSession.setFinal();
        }

        public void setLocation(int x, int y){
            int previousX = this.x;
            int previousY = this.y;
            this.x = x;
            this.y = y;
            //MapManager.setBlock(this.x, this.y, true);
            if(childNode != null){
                this.childNode.setLocation(previousX, previousY);
            }else{
                // update tail grid to false if not new
                if(x != previousX || y != previousY){
                    gameSession.setBlock(previousX, previousY, false);
                }
            }           
        }

        public void displayInfo(){
            System.out.println("Coordinates: "+x+", "+y);
            System.out.println("child node exists? "+ (childNode == null));
        }
    }
}

