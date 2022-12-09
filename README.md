![img5](https://user-images.githubusercontent.com/43931412/206673657-e0820c60-70f2-4135-9df4-4b688c01de24.png)




### 1. Object
*We implemented it as an application so that we can conveniently use the product rental service provided by the software department*

</br>

- Select and place the competitor in the desired position.
- If you select a duplicate player, you will receive a warning message.
- The balance in the upper left corner of the table will be reduced as the player is recruited.
- An error message is output when the balance is exceeded.
- If you form a team, you will be able to do the squad that you made
- You can see it at different angles and locations.


## What is FootballSquadSimulator?
<img src="https://user-images.githubusercontent.com/43931412/206649262-3d9e8576-5efd-46c9-99d5-cde0b72459d8.png"/>

*You may have experience in forming squads through FM or FIFA Online.*
*You can enjoy it on the WEB in 3D!*


### 1. Description
- There are 25 players in the game.
- User have to pay a certain fee to transfer a player.
- Users can choose their preferred players.
- User have to make a squad within $15
- Warning message will be printed if the value exceeds $15
- Make your own best squad!
- You can enjoy it on the WEB in 3D!

</br>

### 2. KeyFeature

![img2](https://user-images.githubusercontent.com/43931412/206653875-fc2e5231-27e3-4740-9648-fd011375f28f.png)

- All players are modeled in 3D, and they can be viewed from various angles by implementing a camera vision.
- The Light Source is fixed in the upper right corner, and you can see that it is applied in a variety of ways depending on the viewer's perspective. 
- You can now see the light source applied to the stand position.
- We expressed it more realistically by applying scaling to match the actual players' height.
- Translation can be applied to keyboard events to place players in the desired position, and rotations can be used to rotate players.

</br>

### 3. Detail 1

![img3](https://user-images.githubusercontent.com/43931412/206654717-95c6b371-43a3-4d87-807a-6bec86084067.png)

- In the case of Scaling, a specific magnification was multiplied by 185cm to adjust the size to the actual height of each player.
- For Translation, apply a keyboard event based on the ZX plane to match the keyboard by 0.5 on the x-axis.
- It is implemented so that the player can move by 0.5 on the z-axis.
- For Rotation, press Q relative to the y-axis and press E to rotate the competitor by approximately 5.7 degrees for each counterclockwise press.

</br>

### 4. Detail 2

![img4](https://user-images.githubusercontent.com/43931412/206654972-a0f7b6bf-7c0c-4c1c-b48f-42fa68652986.png)

- Because we used 3Dobj, we used obj files to load vector values applied to each model and Texture files for image mapping, and loaded MTL files to apply Texture.
- In the process, the material values of MTL and obj files were modified according to the player using various png files corresponding to the textures such as the player's eyes, hair, uniform, and skin.

</br>

### 5. Detail 3

![img5](https://user-images.githubusercontent.com/43931412/206655396-fb0e8807-8315-425f-ad26-c061aac98211.png)

- Most of the obj and Texture files for 3D models were paid, so it was difficult to get files for 25 players, but we got a file for one player and designed a uniform for 25 players, skin, and hair, and applied it to each player.

</br>

### Demo Video

![image1](https://user-images.githubusercontent.com/43931412/146496636-f17b2e29-6f4e-4e15-8b28-b46ff1fcb9be.gif)

### 6. Developer

Developer
201533647 배태원  
201835477 유은석  
201835485 이명우  
201835491 이소은  
