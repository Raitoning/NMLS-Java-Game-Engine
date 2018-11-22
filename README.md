# Mini Java Game Engine

## Engine status: IN DEVELOPMENT, NOT PRODUCTION READY !   
## Development phase: PRE-ALPHA
## Engine version: 18.11.22 
## Version notes:
* Cameras can now be rendered on a specific part of the viewport.
* Cameras are now rendered on the screen via render textures. They are in fact rendered on BufferedImages before blitting them on the final frame buffer.
* The engine can now render multiple cameras at once.
* Cameras are sorted and rendered by their render priority (lower is more important).
* Updated some Javadoc.
* Missing most of the Javadoc.
* Bug with 0 reproductibility affecting the InputAxis class.

# ROADMAP:
* Adding a Scene system similar to Unity.
* [x] Adding a layering system for multiple cameras.
* Adding a Collision Matrix to get better control over physics simulations.
* Adding an Audio subsystem.

Welcome to Namaeless Studio's Mini Java Game Engine official repository !  
Here you will find the lastest released version of the game engine.  

# What is this game engine ?

I developped this game engine as a side project for some Java mini game assignements at university.  
This game engine is heavily inspired from the Unity game engine (From Unity Technologies).  
It looks a lot like Unity and try to stay as close as possible to Unity's API, but it doesn't come even close to it's functionnalities, performances or platform compatibility.

# Who can use it ?

Anyone who wants a Java "clone" of Unity can download it and use it.  
It's functionning is almost similar to Unity: Put all your code, textures, sounds, etc in an Asset folder next to the engine package and everything works almost out of the box.  
