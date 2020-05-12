package net.alfiesmith.multicommand;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Getter
@RequiredArgsConstructor
public class MultiCommand {

    private final String label, permission;
    private final boolean consoleExecute;
    private final List<String> commands;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MultiCommand that = (MultiCommand) o;
        return Objects.equals(label, that.label) &&
                Objects.equals(commands, that.commands);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label, commands);
    }
}
