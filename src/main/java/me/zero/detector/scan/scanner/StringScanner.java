package me.zero.detector.scan.scanner;

import me.zero.detector.scan.ClassScanner;
import me.zero.detector.util.io.InternalFileReader;

import java.util.List;

/**
 * @author Brady
 * @since 9/27/2017 2:28 PM
 */
abstract class StringScanner implements ClassScanner {

    static List<String> BAD_STRINGS = List.of(InternalFileReader.read("strings.txt").split("\n"));
}
