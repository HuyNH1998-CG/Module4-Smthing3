package bigg.formatter;

import bigg.model.Branch;
import bigg.service.IBranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;

public class BranchFormatter implements Formatter<Branch> {
    private IBranchService branchService;

    @Autowired
    public BranchFormatter(IBranchService branchService) {
        this.branchService = branchService;
    }

    @Override
    public Branch parse(String text, Locale locale) throws ParseException {
        Optional<Branch> branchOptional = branchService.findById(Integer.parseInt(text));
        return branchOptional.orElse(null);
    }

    @Override
    public String print(Branch object, Locale locale) {
        return "[" + object.getId() + "," + object.getName() + "]";
    }
}
