package com.fitapp.vizo.fitnessapp.services;

/**
 * Created by Guest on 12/9/16.
 */
public class WgerConversions {
    public static String toMuscleString(int muscle) {
        if (muscle == 2) return "Deltoid";
        if (muscle == 1) return "Biceps";
        if (muscle == 13) return "Brachialis";
        if (muscle == 14) return "Obliques";
        if (muscle == 4) return "Pectoralis Major";
        if (muscle == 6) return "Rectus Abdominis";
        if (muscle == 3) return "Serratus Anterior";
        if (muscle == 10) return "Quadriceps";
        if (muscle == 11) return "Biceps Femoris";
        if (muscle == 7) return "Gastrocnemius";
        if (muscle == 8) return "Gluteus Maximus";
        if (muscle == 12) return "Latissimus Dorsi";
        if (muscle == 15) return "Soleus";
        if (muscle == 9) return "Trapezius";
        if (muscle == 5) return "Triceps Brachii";
        return "Muscle Not Found";
    }
    public static String toEquipmentString(int equipment) {
        if (equipment == 2) return "SZ-Bar";
        if (equipment == 1) return "Barbell";
        if (equipment == 4) return "Gym mat";
        if (equipment == 6) return "Pull-up bar";
        if (equipment == 3) return "Dumbbell";
        if (equipment == 10) return "Kettlebell";
        if (equipment == 7) return "Bodyweight exercise";
        if (equipment == 8) return "Bench";
        if (equipment == 9) return "Incline bench";
        if (equipment == 5) return "Swiss Ball";
        return "Equipment Not Found";
    }
}