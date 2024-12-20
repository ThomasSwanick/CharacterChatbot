package entity.bot;

/**
 * Factory for creating original custom bots.
 */
public class CustomBotFactory implements BotFactory {

    // Just need the below method so it can implement botfactory.
    @Override
    public CustomBot create() {
        return new CustomBot("AI", "helpful assistant");
    }

    public CustomBot create(String name, String occupation) {
        return new CustomBot(name, occupation);
    }

}
