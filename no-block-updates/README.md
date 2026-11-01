# No Block Updates (Fabric, Minecraft Java 26.3)

Adds one game rule. Off by default, saved per world.

    /gamerule noblockupdates:disable_block_updates true    # block updates OFF
    /gamerule noblockupdates:disable_block_updates false   # back to vanilla

While it's on, breaking or placing a block no longer updates its neighbours:
flowers/grass/torches stay floating, sand and gravel don't fall, water doesn't
flow into new gaps, and redstone/observers don't react.

## Build

Needs JDK 25.

    ./gradlew build          (Windows: gradlew.bat build)

The mod jar ends up in `build/libs/no-block-updates-1.0.0.jar`
(ignore the `-sources` jar). Put it in `.minecraft/mods` together with
Fabric API for 26.3.

To test in a dev client: `./gradlew runClient`.
