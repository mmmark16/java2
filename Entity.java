package com.company;
import java.util.List;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Entity {
    private static int idCounter = 1;
    private final long id = idCounter++;
    private String title;
    private double posX;
    private double posZ;
    private boolean agressive;
    protected int maxHealth;
    protected int health;
    private int attackDamage;
    private boolean dead = false;
    private Entity target;
    private World world;

    public void update() {
        if (target == null) {
            searchTarget();
            if (target == null) return;
        } else if (Math.pow(posZ - target.getPosZ(), 2) + Math.pow(posX - target.getPosX(), 2) > 2) {
            if (posX < target.getPosX()) posX++;
            else if (posX > target.getPosX()) posX--;
            if (posZ < target.getPosZ()) posZ++;
            else if (posZ > target.getPosZ()) posZ--;
        } else attack(target);
    }
    public void searchTarget(){
        List<Entity> entities = world.getEntitiesNearEntity(this, 20);
        for(Entity e : entities){
            if (e.isAgressive()) {
                target = e;
            }
        }
    }
    public void attack(Entity entity) {

        if (entity instanceof EntityPlayer) {
            if (entity.health <= 0){
                entity.dead = true;
                System.out.println(this.title + " kill " + entity.title);
            } else {
                entity.health -= this.attackDamage  + 0.5 * GameServer.getInstance().getDifficulty();
                this.health -= entity.attackDamage;
            }
            if (this.health <= 0){
                this.dead = true;
                System.out.println(entity.title + " kill " + this.title);
            }
        } else {
            if (entity.health <= 0 && !entity.dead){
                entity.dead = true;
                System.out.println(this.title + " kill " + entity.title);
            } else {
                entity.health -= this.attackDamage  + 0.5 * GameServer.getInstance().getDifficulty();
            }
        }
    }
    public Entity(String title, double posX, double posZ, boolean agressive, int maxHealth, int health, int attackDamage, World world) {
        this.title = title;
        this.posX = posX;
        this.posZ = posZ;
        this.agressive = agressive;
        this.maxHealth = maxHealth;
        this.health = health;
        this.attackDamage = attackDamage;
        this.world = world;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosZ() {
        return posZ;
    }

    public void setPosZ(double posZ) {
        this.posZ = posZ;
    }

    public boolean isAgressive() {
        return agressive;
    }

    public void setAgressive(boolean agressive) {
        this.agressive = agressive;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public Entity getTarget() {
        return target;
    }

    public void setTarget(Entity target) {
        this.target = target;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", posX=" + posX +
                ", posZ=" + posZ +
                ", agressive=" + agressive +
                ", maxHealth=" + maxHealth +
                ", health=" + health +
                ", attackDamage=" + attackDamage +
                ", dead=" + dead +
                ", target=" + target +
                ", world=" + world +
                '}';
    }
}