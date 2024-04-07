ServerEvents.recipes(event => {
    console.info("Initializing apotheosis recipes")

    //Shaped recipes
    event.shaped(
        Item.of('minecraft:stone_axe', '{AttributeModifiers:[{Amount:0.029999999329447746d,AttributeName:"attributeslib:crit_chance",Name:"cmd-generated-modif",Operation:0,Slot:"mainhand",UUID:[I;-612987435,1767197828,-1628093794,-2077050212]},{Amount:1.0d,AttributeName:"forge:block_reach",Name:"cmd-generated-modif",Operation:0,Slot:"mainhand",UUID:[I;1388498967,-242792643,-1621406619,1047731126]}],Damage:0}'),
        [
            's ',
            'st'
        ],
        {
            s: 'dotv:mana_infused_stone',
            t: 'dotv:wooden_tool_binding'
        }
    );
    event.shaped(
        Item.of('minecraft:stone_pickaxe', '{AttributeModifiers:[{Amount:0.07000000029802322d,AttributeName:"attributeslib:mining_speed",Name:"cmd-generated-modif",Operation:0,Slot:"mainhand",UUID:[I;825761897,333859181,-1778387862,1770349840]},{Amount:1.0d,AttributeName:"forge:block_reach",Name:"cmd-generated-modif",Operation:0,Slot:"mainhand",UUID:[I;-2021669898,-1310637547,-1277844776,-1244948161]}],Damage:0}'),
        [
            'ss',
            'ts'
        ],
        {
            s: 'dotv:mana_infused_stone',
            t: 'dotv:wooden_tool_binding'
        }
    )
    event.shaped(
        Item.of('minecraft:stone_shovel', '{AttributeModifiers:[{Amount:0.10000000149011612d,AttributeName:"attributeslib:crit_damage",Name:"cmd-generated-modif",Operation:0,Slot:"mainhand",UUID:[I;674988355,2064007258,-2105441215,231856276]},{Amount:1.0d,AttributeName:"forge:block_reach",Name:"cmd-generated-modif",Operation:0,Slot:"mainhand",UUID:[I;1579805030,-1959244252,-2146551543,-218883785]}],Damage:0}'),
        [
            's',
            't'
        ],
        {
            s: 'dotv:mana_infused_stone',
            t: 'dotv:wooden_tool_binding'
        }
    )
    event.shaped(
        Item.of('minecraft:stone_hoe', '{AttributeModifiers:[{Amount:1.0d,AttributeName:"minecraft:generic.luck",Name:"cmd-generated-modif",Operation:0,Slot:"mainhand",UUID:[I;1702046467,2052475634,-1363737699,-374317883]},{Amount:1.0d,AttributeName:"forge:entity_reach",Name:"cmd-generated-modif",Operation:0,Slot:"mainhand",UUID:[I;1056566769,-1164098175,-1204615289,1022039354]}],Damage:0}'),
        [
            'ss',
            ' t'
        ],
        {
            s: 'dotv:mana_infused_stone',
            t: 'dotv:wooden_tool_binding'
        }
    )
    event.shaped(
        Item.of('minecraft:stone_sword', '{AttributeModifiers:[{Amount:0.20000000298023224d,AttributeName:"attributeslib:experience_gained",Name:"cmd-generated-modif",Operation:0,Slot:"mainhand",UUID:[I;-366248595,-1104525026,-1275847235,-1806595149]},{Amount:0.5d,AttributeName:"forge:entity_gravity",Name:"cmd-generated-modif",Operation:0,Slot:"mainhand",UUID:[I;1227091481,-254065030,-1824141588,-1646442489]}],Damage:0}'),
        [
            ' s',
            't '
        ],
        {
            s: 'dotv:mana_infused_stone',
            t: 'dotv:wooden_tool_binding'
        }
    )

    console.info("Apotheosis recipes complete")
})