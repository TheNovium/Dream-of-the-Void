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
        Item.of('dotv:wooden_tool_binding', 1),
        [
            ' E',
            'E '
        ],
        {
            E: 'mysticalagriculture:wood_essence'
        }
    );



    console.info("DotV Recipes Complete");
})