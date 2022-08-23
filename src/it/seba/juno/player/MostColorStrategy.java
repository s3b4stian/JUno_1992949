package it.seba.juno.player;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import it.seba.juno.card.UnoCard;
import it.seba.juno.card.UnoColor;

/**
 * It is the concrete implementation of a color strategy where the color is
 * choose checking the color of all player cards and selecting the color that
 * appears most.
 * 
 * @author Sebastian Rapetti
 *
 */
public class MostColorStrategy extends AbstractColorStrategy {

    /**
     * Returns the color that appears most in cards, if there isn't a predominant
     * color, return random.
     * 
     * @return the color
     */
    @Override
    public UnoColor changeColor() {

        if (cards.size() > 0) {

            Map<UnoColor, Integer> cMap = cards.stream()
                    // remove wild cards
                    .filter(card -> card.hasColor())
                    // collect to map only color cards
                    .collect(Collectors.toMap(
                            // element used as key, the card color
                            UnoCard::getColor,
                            // value, every time 1
                            x -> 1,
                            // Function used to resolve when a key has more than one value
                            Integer::sum));

            if (!cMap.isEmpty()) {
                // reference
                // https://www.baeldung.com/java-find-map-max
                Optional<Map.Entry<UnoColor, Integer>> maxColor = cMap.entrySet()
                        // to stream
                        .stream()
                        // take the max using comparator
                        .max(Comparator.comparing(Map.Entry::getValue));

                return maxColor.get().getKey();
            }
        }

        return UnoColor.getRandom();
    }
}
