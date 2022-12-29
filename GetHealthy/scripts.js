// Function called by user of website when they click calculate button
function calculate() {
  // Extracts the weight, height, age , and average days active that is entered by user
  var weight = parseFloat(document.getElementById("weight").value);
  var height = parseFloat(document.getElementById("height").value);
  var age = parseFloat(document.getElementById("age").value);
  var avgDaysActive = parseFloat(document.getElementById("days").value);
  //   Uses entered weight, height, and age to call second function to calculate bmr
  var bmr = calculateBMR(weight, height, age);
  //   Uses entered average days active to calculate activity level
  var activityLevel = calculateActivityLevel(avgDaysActive);
  //   Uses BMR and activity level to calculate macro split
  calculateMacroSplit(bmr, activityLevel);
}

//Calculates and displays user macro split
function calculateMacroSplit(bmr, activityLevel) {
  var protein = document.getElementById("protein");
  var carbs = document.getElementById("carbs");
  var fat = document.getElementById("fat");
  var totalCalories = document.getElementById("total");
  //   Displays total calories, needed calories from protein, carbs, and fat on our macro calculator webpage
  totalCalories.textContent = (bmr * activityLevel).toFixed(2);
  protein.textContent = (bmr * activityLevel * 0.4).toFixed(2) + "g";
  carbs.textContent = (bmr * activityLevel * 0.4).toFixed(2) + "g";
  fat.textContent = (bmr * activityLevel * 0.2).toFixed(2) + "g";
}

//Calcualtes individuals Basal Metabolic Rate
function calculateBMR(weight, height, age) {
  var bmr = 655 + 4.35 * weight + 4.7 * height - 4.7 * age;
  return bmr;
}

// Calculates the activity level for individual
function calculateActivityLevel(avgDaysActive) {
  var activityLevel = 0;
  //   Activity level depends on the number of days your active per week
  if (avgDaysActive <= 1) {
    activityLevel = 1.2;
  } else if (avgDaysActive > 1 && avgDaysActive < 3) {
    activityLevel = 1.375;
  } else if (avgDaysActive >= 3 && avgDaysActive <= 5) {
    activityLevel = 1.55;
  } else {
    activityLevel = 1.725;
  }
  return activityLevel;
}
