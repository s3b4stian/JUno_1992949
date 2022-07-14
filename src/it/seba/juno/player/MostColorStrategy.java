package it.seba.juno.player;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import it.seba.juno.card.UnoCard;
import it.seba.juno.card.UnoColor;

public class MostColorStrategy extends AbstractColorStrategy {

    @Override
    public UnoColor changeColor() {

        if (cards.size() > 0) {

            Map<UnoColor, Integer> cMap = cards.stream()
                .filter(card -> card.hasColor())    // remove wild cards
                .collect(Collectors.toMap(          // collect to map only color cards
                UnoCard::getColor,                  // element used as key, the card color
                x -> 1,                             // value, every time 1
                Integer::sum)                       // Function used to resolve when a key has more than one value
            );

            if (!cMap.isEmpty()) {

                // reference
                // https://www.baeldung.com/java-find-map-max
                Optional<Map.Entry<UnoColor, Integer>> maxColor = cMap.entrySet().stream()
                        .max(Comparator.comparing(Map.Entry::getValue));

                return maxColor.get().getKey();
            }
        }

        return UnoColor.getRandom();
    }
}
