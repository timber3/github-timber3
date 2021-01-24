package com.example.gameendgine;

import java.util.ArrayList;

class MonsterSequence {
    int time;
    String name;
    int x, y;
    public MonsterSequence(int time, String name, int x, int y)
    {
        this.time = time;
        this.name = name;
        this.x = x;
        this.y = y;

    }
}
public class GameEngine {
    Player player;
    String monsterData = "1 M1 0 0, 3 M3 0 3, 4 M2 0 0";
    // 시작시간 몬스터이름 생성x좌표 생성y좌표 ,

    ArrayList<MonsterSequence> monsterSequences = new ArrayList<MonsterSequence>();

    public GameEngine () {

        player = new Player();
        parseMonsterSequence();
    }


    void parseMonsterSequence() {
        String monsterData = "1 M1 0 0, 3 M3 0 3, 4 M2 0 0";
        String[] monsterArray = monsterData.split(",");

        for (String monsterStr : monsterArray) {
            String[] monsterValueArray = monsterStr.split(" ");
            monsterSequences.add( new MonsterSequence(
                    Integer.parseInt(monsterValueArray[0]),
                            monsterValueArray[1],
                            Integer.parseInt(monsterValueArray[2]),
                            Integer.parseInt(monsterValueArray[3])
            )
            );
        }
    }
    // 비행기 게임
    public void run() {

        while(!checkGameOver()) // 주인공 남은 체력이 0이면 break;
        {
            current_time ++;
            generateMonsters(); // 몬스터 출현
            moveMonsters();    // 몬스터 이동
            movePlayer();       // 주인공 이동

            processMonsterPlayerCollision();   // 몬스터와 주인공이 서로 만났는지 확인

            processMonsterPlayerWeaponCollision();   // 주인공 미사일과 몬스터가 서로 만났는지 확인

        }
    }

//    ArrayList<Monster1> m1Array = new ArrayList<Monster1>();
//    ArrayList<Monster2> m2Array = new ArrayList<Monster2>();

    ArrayList<Entity> monsterArray = new ArrayList<Entity>();
    ArrayList<Weapon> weaponArray = new ArrayList<Weapon>();

    int current_time = 0;

    public void generateMonsters() {


        MonsterSequence monsterSequence = monsterSequences.get(0);
        if (monsterSequence.time <= current_time) {
            monsterArray.add( MonsterFactory.generate(monsterSequence))

            monsterSequences.remove (0);

        }
    }

    public void moveMonsters(){

        for (Entity monster: monsterArray)
        {
            monster.move();
        }
    }



    public void movePlayer() {
        if (){// 왼쪽 키가 눌리면
            player.moveLeft();
        }
        else if () {// 오른쪽쪽키가 눌리면
            player.moveRight();
        }

        if () //shoot 키가 눌렸으면
        {
            weaponArray.add(new Weapon(player));
        }
        for (Weapon weapon : weaponArray) {
            weapon.move();
        }
    }

    public void processMonsterPlayerCollision() {}
    public void processMonsterPlayerWeaponCollision() {}
    public boolean checkGameOver() { return false;}
}

class Entity {
    int x, y;
    String imagepath; //"/images/slime.jpg"
    void move() {

    }
}

class Player extends Entity {
    void moveLeft() { }
    void moveRight() { }
    void Shoot() { }
}


class Monster1 extends Entity {

    public Monster1 (int x ,int y)
    {
        this.x = x;
        this.y = y;

    }

    void move() {
        y--;
    }

}

// 몬스터를 새로 추가할때 여기서 추가하는 파트
class MonsterFactory {
    static Entity generate(MonsterSequence monsterSequence) {
        switch(monsterSequence.name) {
            case "M1" :
                return new Monster1(x,y);
            case "M2" :
                return new Monster2(x,y);

        }
    }

}



class Monster2 extends Entity {

    public Monster2 (int x ,int y)
    {
        this.x = x;
        this.y = y;

    }

    void move() {
        y--;
        x--;
    }
}

class Weapon extends Entity {
    public Weapon(Player player) {
        x = player.x;
        y = player.y + 1;
    }
    void move() {y++;}
}