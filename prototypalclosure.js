// contains code for protopal inheritance, closure, callbacks, promises, async/await
// use this command to run: node filename.js
// prototypal inheritance

function Animal(name, sound) {
  this.name = name;
  this.sound = sound;
}
Animal.prototype.makeSound = function () {
  console.log(this.name + " makes a sound: " + this.sound);
};
function Dog(name, sound, breed) {
  Animal.call(this, name, sound);
  this.breed = breed;
}
Dog.prototype = Object.create(Animal.prototype);
Dog.prototype.constructor = Dog;
Dog.prototype.bark = function () {
  console.log(this.name + " barks: Woof! Woof!");
};

const myDog = new Dog("Buddy", "Woof", "Golden Retriever");
myDog.makeSound();
myDog.bark();

console.log(myDog instanceof Dog);
console.log(myDog instanceof Animal);

// closure

function outerFunction(outerVariable) {
  return function innerFunction(innerVariable) {
    console.log("Outer Variable:", outerVariable);
    console.log("Inner Variable:", innerVariable);
  };
}
const newFunction = outerFunction("outside");
newFunction("inside");

// callbacks

function greetUser(name, callback) {
  console.log("Hello " + name + "!");
  callback();
}

function displayGreetingMessage() {
  console.log("Welcome to our platform!");
}

greetUser("Alice", displayGreetingMessage);

// promises
function fetchData(success) {
  return new Promise((resolve, reject) => {
    console.log("Fetching data...");
    setTimeout(() => {
      success
        ? resolve("Data fetched successfully!")
        : reject("Error: Failed to fetch data.");
    }, 2000);
  });
}

fetchData(true)
  .then((response) => console.log(response))
  .catch((error) => console.log(error));

//  Async/Await

function fetchData(success) {
  return new Promise((resolve, reject) => {
    console.log("Fetching data...");
    setTimeout(() => {
      success
        ? resolve("Data fetched successfully!")
        : reject("Error: Failed to fetch data.");
    }, 2000);
  });
}

async function getData() {
  try {
    const result = await fetchData(true);
    console.log(result);
  } catch (error) {
    console.log(error);
  }
}

getData();
