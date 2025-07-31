# True Sight (Fabric)

A lightweight client-side Fabric mod that renders invisible players as fully visible. If a player is under the Invisibility status effect, you still see their full model.

## Features
- Client-side only: no server installation required
- Only affects players; mobs and other entities still use vanilla invisibility
- Compatible across Minecraft 1.21 through 1.22 (built against 1.21.8)

## Compatibility
- Minecraft: 1.21 – 1.22 (range declared in [fabric.mod.json](src/main/resources/fabric.mod.json:34))
- Built/Mapped against: 1.21.8 + Yarn ([gradle.properties](gradle.properties:7))
- Fabric Loader: >= 0.16.14 ([gradle.properties](gradle.properties:9))
- Fabric API: 0.130.0+1.21.8 ([gradle.properties](gradle.properties:18))
- Java: 21

## Install (end users)
1) Install Fabric Loader for 1.21.8 (or any 1.21–1.22 version).
2) Download Fabric API matching your game version.
3) Download the release jar from GitHub/Modrinth and put it in your .minecraft/mods folder.
4) Launch the game.

## Build (developers)
Requirements: JDK 21, Internet access for Gradle.

Windows:
- .\gradlew build

macOS/Linux:
- ./gradlew build

Output:
- [build/libs/true-sight-1.0.0.jar](build/libs/true-sight-1.0.0.jar:1)

## Dev run
- .\gradlew runClient (Windows) or ./gradlew runClient (Unix)

## Test in-game (no extra mods)
- Singleplayer: `/effect give @s minecraft:invisibility 600 0 true`, press F5 to third-person. You should remain fully visible.
- LAN/Two instances: Open to LAN with cheats ON, join from another instance, make that player invisible. They appear visible on your screen.

## Implementation notes
- Mixin: [PlayerInvisibilityBypassMixin.java](src/client/java/see/invisible/mixin/client/PlayerInvisibilityBypassMixin.java:17)
  - [PlayerInvisibilityBypassMixin.trueSight_forceVisible_isInvisible()](src/client/java/see/invisible/mixin/client/PlayerInvisibilityBypassMixin.java:21) forces `Entity.isInvisible()` to false for invisible players.
  - [PlayerInvisibilityBypassMixin.trueSight_forceVisible_isInvisibleTo()](src/client/java/see/invisible/mixin/client/PlayerInvisibilityBypassMixin.java:30) forces `Entity.isInvisibleTo(viewer)` to false for invisible players.
- Client init: [TrueSightClient.onInitializeClient()](src/client/java/see/invisible/TrueSightClient.java:7)
- Common init: [TrueSight.onInitialize()](src/main/java/see/invisible/TrueSight.java:17)
- Metadata: [fabric.mod.json](src/main/resources/fabric.mod.json:1)
- Loom/Gradle: [build.gradle](build.gradle:2) uses Loom version from [gradle.properties](gradle.properties:10)

## Repository hygiene
- Ignored build artifacts:
  - .gradle/
  - build/
  - out/
  - bin/
  - run/
  - remappedSrc/
  - IDE metadata (.idea/, .vscode/, etc.)
- See [.gitignore](.gitignore:1) for the complete list. Build outputs and dev folders have been removed from the repo working tree.

## Modrinth/GitHub release checklist
- Build a fresh jar: `.\gradlew build`
- GitHub:
  - Create a tag (e.g., v1.0.0).
  - Draft a release and upload [build/libs/true-sight-1.0.0.jar](build/libs/true-sight-1.0.0.jar:1).
  - Add description, changelog, and link to [LICENSE](LICENSE:1).
- Modrinth:
  - Create/Update project page.
  - Upload the same jar.
  - Set “Game Versions” to 1.21.8 (and other 1.21–1.22 versions you’ve tested).
  - Set “Loaders” to Fabric.
  - Mark “Client Side” environment.
  - Add a dependency on Fabric API.
  - Use project icon at [assets/true-sight/icon.png](src/main/resources/assets/true-sight/icon.png:1) and copy the README overview.

## License
- This project is licensed under CC0-1.0. See [LICENSE](LICENSE:1).

## Credits
- Based on Fabric example structure. True Sight implementation by the TrueSight Team.
