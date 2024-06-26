ServerEvents.recipes(event => {
    console.info("Initializing vanilla recipes...");

    //Remove vanilla recipes
    event.remove({output: 'minecraft:crafting_table'});

    //Shapeless recipes
    event.shapeless(
        Item.of('minecraft:sand', 1),
        [
            'minecraft:dirt',
            'minecraft:cobblestone'
        ]
    );
    event.shapeless(
        Item.of('minecraft:wooden_hoe', 1),
        [
            'dotv:wooden_hoe_tool',
            'dotv:wooden_tool_binding'
        ]
    );
    event.shapeless(
        Item.of('minecraft:wooden_pickaxe', 1),
        [
            'dotv:wooden_pickaxe_tool',
            'dotv:wooden_tool_binding'
        ]
    );
    event.shapeless(
        Item.of('minecraft:clay', 1),
        [
            '#dotv:fiber',
            'minecraft:sand',
            'minecraft:dirt'
        ]
    );
    event.shapeless(
        Item.of('minecraft:grass_block', 1),
        [
            'minecraft:dirt',
            'minecraft:moss_block'
        ]
    )

    console.info("Vanilla Recipes Complete");
})