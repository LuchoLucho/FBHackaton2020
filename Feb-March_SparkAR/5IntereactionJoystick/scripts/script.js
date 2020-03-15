/**
 * (c) Facebook, Inc. and its affiliates. Confidential and proprietary.
 */

//==============================================================================
// Welcome to scripting in Spark AR Studio! Helpful links:
//
// Scripting Basics - https://fb.me/spark-scripting-basics
// Reactive Programming - https://fb.me/spark-reactive-programming
// Scripting Object Reference - https://fb.me/spark-scripting-reference
// Changelogs - https://fb.me/spark-changelog
//==============================================================================
// Load in the required modules
// Load in the required modules
//const HandTracking = require('HandTracking'); INSTAGRAM DOESN'T SUPPORT THIS!
const Scene = require('Scene');
const TouchGestures = require('TouchGestures');
const Diagnostics = require('Diagnostics');
const Animation = require('Animation');
const Audio = require('Audio');

// Locate the plane and focal distance in the Scene
const joystickBody = Scene.root.find('Joystick_Cube.003');
const marioPlane = Scene.root.find('Mario_Plane.001');
const camera1 = Scene.root.find('Camera');

const baseDriverParameters = {
    durationMilliseconds: 400,
    loopCount: 2,
    mirror: true
};

const baseDriver = Animation.timeDriver(baseDriverParameters);
//baseDriver.start();

const baseSampler = Animation.samplers.easeInCubic(0,0.05);
const baseAnimation = Animation.animate(baseDriver,baseSampler);
const baseTransform = marioPlane.transform;
//baseTransform.scaleX = baseAnimation;
baseTransform.y = baseAnimation;
//baseTransform.scaleZ = baseAnimation;

var isJumping = false;

const playbackController =
Audio.getPlaybackController('audioPlaybackController0');

TouchGestures.onTap(joystickBody).subscribe(function (gesture) {

    // Switch materials depending on which one is currently applied to the plane
    //marioPlane.Visible = false;
    //marioPlane.transform.x.Add
    //joystickBody.hidden = true;
    //Diagnostics.log("Tap! marioPlane.transform.x = " + marioPlane.transform.x);
    //Diagnostics.log("Tap! camera1.transform.x = " + camera1.transform.x);
    if (!isJumping)
    {
        baseDriver.start();
        playbackController.setPlaying(true);
    }
    
  });

  baseDriver.onCompleted().subscribe(function()
  {
    isJumping = false;
    Diagnostics.log("Jump completed!");
    baseDriver.reset();
    playbackController.reset();
  }
  );
  

//const focalDistance = Scene.root.find('Focal Distance');

// Store a reference to a detected hand
//const hand = HandTracking.hand(0);

// Store the z-axis position signal of the focal distance
//const focalDistanceZPosition = focalDistance.transform.z;

// Bind the cameraTransform of the hand to the plane's transform
//plane.transform = hand.cameraTransform;

// Additionally overwrite the z-axis values with distance taken into account
//plane.transform.z = hand.cameraTransform.z.sub(focalDistanceZPosition);

// Bind the hidden property of the plane to a boolean signal that returns true
// when no hands have been detected
//plane.hidden = HandTracking.count.eq(0);