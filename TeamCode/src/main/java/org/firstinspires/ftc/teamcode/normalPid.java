package org.firstinspires.ftc.teamcode;

public class normalPid {
    private double error;
    private double deltaTime;
    private double previousTime;
    private double d;
    private double previousError;
    private double KI;
    private double KP;
    private double KD;
    private double i;
    private double power;

    public double runPid(double currentLocation, double targetLocation){
        error = targetLocation - currentLocation;
        deltaTime = System.currentTimeMillis() - previousTime;
        d = (error - previousError)/deltaTime;
        if(currentLocation > targetLocation*0.8){
            i += deltaTime * error;
        }
        error = previousError;
        previousTime = System.currentTimeMillis();
        power = (KP*error) + (KI*i) + (KD*d);
        return power;
    }

    public void setKP(double KP){
        this.KP = KP;
    }
    public void setKI(double KI){
        this.KI = KI;
    }
    public void setKD(double KD){
        this.KD = KD;
    }
    public double getError(){
        return error;
    }
}
