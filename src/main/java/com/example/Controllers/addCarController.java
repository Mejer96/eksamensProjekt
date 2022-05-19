package com.example.Controllers;

import com.example.Models.Car;
import com.example.Repositiories.AddCarsRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLException;

@Controller
public class addCarController {

    AddCarsRepository addCarsRepository =new AddCarsRepository();

    @GetMapping("/addCars")
    public String addCars(){ return "add-cars";}

    @PostMapping("/addCars")
    public String registrerCars(WebRequest dataFromForm) throws SQLException {
        String carModel = dataFromForm.getParameter("carModel");
        String brand = dataFromForm.getParameter("brand");
        String carEmissionCost = dataFromForm.getParameter("carbonEmissionCost");
        String registrationCost = dataFromForm.getParameter("registrationCost");
        String equipmentLevel = dataFromForm.getParameter("equipmentLevel");

        String isRented = dataFromForm.getParameter("isRented");
        Boolean isRentedB;
        if (isRented.equals("yes")) {
            isRentedB = true;
        } else {
            isRentedB = false;
        }

        String isDamaged = dataFromForm.getParameter("isDamaged");
        Boolean isDamagedB;
        if(isDamaged.equals("yes")) {
            isDamagedB = true;
        }else{
            isDamagedB =false;
        }

        System.out.println("hej" + carEmissionCost);

       Car newCar = new Car(carModel, brand, Integer.parseInt(carEmissionCost),Integer.parseInt(registrationCost), equipmentLevel, isRentedB,isDamagedB);
       // Car newCar = new Car("toyo", "hej",3445,4335,"gfdfg",true,true);
        addCarsRepository.addCars(newCar);
        /*kalde service der validerere, som adder til database hvis valideres*/
        return "redirect:/index";
    }

}
