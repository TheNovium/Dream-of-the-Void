ServerEvents.recipes(event => {
    console.info("Initializing DotV recipes...");

    //Shaped recipes
    event.shaped(
        Item.of('dotv:mystical_seeds', 1),
        [
            'EE',
            'EE'
        ],
        {
            E: 'mysticalagriculture:wood_essence'
        }
    );
    event.shaped(
        Item.of('dotv:stone_crafter', 1),
        [
            'EE',
            'EE'
        ],
        {
            E: 'mysticalagriculture:stone_essence'
        }
    );
    event.shaped(
        Item.of('dotv:wooden_hoe_tool', 1),
        [
            'E ',
            ' E'
        ],
        {
            E: 'mysticalagriculture:wood_essence'
        }
    );
    event.shaped(
        Item.of('dotv:wooden_pickaxe_tool', 1),
        [
            'EE',
            ' E'
        ],
        {
            E: 'mysticalagriculture:wood_essence'
        }
    );
    event.shaped(
        Item.of('dotv:wooden_tool_binding', 1),
        [
            ' E',
            'E '
        ],
        {
            E: 'mysticalagriculture:wood_essence'
        }
    );

    //Shapeless
    event.shapeless(
        Item.of('dotv:vile_blend', 1),
        [
            'minecraft:rotten_flesh',
            'minecraft:bone',
            'minecraft:gunpowder',
            'minecraft:string'
        ]
    )

    console.info("DotV Recipes Complete");
})