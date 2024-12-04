package cat.itacademy.s05.t02.n01.S05T02N01.persistence.entities;

import cat.itacademy.s05.t02.n01.S05T02N01.exception.PetActionsException;
import cat.itacademy.s05.t02.n01.S05T02N01.persistence.enums.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pet {
    private String name;
    private PetType type;
    private PetColor color;
    
    @Builder.Default
    private int energy = 75;
    @Builder.Default
    private int hunger = 25;
    @Builder.Default
    private int happiness = 75;
    @Builder.Default
    private PetMood petMood = PetMood.HAPPY;
    @Builder.Default
    private PetLocation petLocation = PetLocation.HOME;
    @Builder.Default
    private List<Accessory> accessories = new ArrayList<>();
    @Builder.Default
    private LocalDateTime lastInteraction=LocalDateTime.now();
    @Builder.Default
    private LocalDateTime lastScheduledUpdate=LocalDateTime.now();
    
    public void setEnergy(int energy) {
        this.energy = Math.max(0, Math.min(energy, 100));
    }
    
    public void setHunger(int hunger) {
        this.hunger = Math.max(0, Math.min(hunger, 100));
    }
    
    public void setHappiness(int happiness) {
        this.happiness = Math.max(0, Math.min(happiness, 100));
    }
    
    public void feed() {
        
        if (hunger>0) {
            setHunger(getHunger() - 5);
            setEnergy(getEnergy() + 5);
            update("userInteraction");
        } else {
            throw new PetActionsException("The pet is not hungry");
        }
    }
    
    public void play() {
        if (energy>0) {
            setHunger(getHunger() + 5);
            setEnergy(getEnergy() - 5);
            setHappiness(getHappiness() + 5);
            update("userInteraction");
        } else {
            throw new PetActionsException("The pet is too tired");
        }
    }
    
    public void petPet() {
        if (energy>10) {
            setEnergy(getEnergy() - 1);
            setHappiness(getHappiness() + 5);
            update("userInteraction");
        } else {
            throw new PetActionsException("The pet needs to rest");
        }
    }
    
    public void sleepPet() {
        if (energy<=75) {
            setHunger(getHunger() + 5);
            setEnergy(getEnergy() + 15);
            update("userInteraction");
        } else {
            throw new PetActionsException("The pet not need to rest");
        }
    }
    
    public void changeLocation(PetLocation newLocation) {
        if (newLocation!=getPetLocation()) {
            setPetLocation(newLocation);
            switch (newLocation) {
                case HOME -> setHappiness(getHappiness() + 5);
                case PARK -> setHappiness(getHappiness() + 20);
                case VET -> setHappiness(getHappiness() - 50);
            }
            update("userInteraction");
        } else {
            throw new PetActionsException("The pet is already in this location");
        }
    }
    
    public void addAccessory (Accessory accessory) {
       
        if (!accessories.contains(accessory)) {
            accessories.add(accessory);
            setHappiness(getHappiness()+10);
            update("userInteraction");
        } else {
            throw new PetActionsException("The pet already has this accessory");
        }
    }
    
    public void removeAccessory (Accessory accessory) {
        if (accessories.contains(accessory)) {
            accessories.remove(accessory);
            setHappiness(getHappiness()-10);
            update("userInteraction");
        } else {
            throw new PetActionsException("The pet does not have this accessory");
        }
    }
    
    public void update(String interactionType) {
        if (happiness == 100) {
            setPetMood(PetMood.EXCITED);
        } else if (happiness >= 50 && happiness <= 99) {
            setPetMood(PetMood.HAPPY);
        } else if (happiness >= 20 && happiness <= 49) {
            setPetMood(PetMood.APATHETIC);
        } else if (happiness >= 1 && happiness <= 19) {
            setPetMood(PetMood.SAD);
        } else if (happiness == 0) {
            setPetMood(PetMood.ANGRY);
        }
        
        if (energy <= 20) {
            setPetMood(PetMood.TIRED);
        }
        
        if (interactionType.equalsIgnoreCase("userInteraction")) {
            setLastInteraction(LocalDateTime.now());
        } else {
            setLastScheduledUpdate(LocalDateTime.now());
        }
    }
}
