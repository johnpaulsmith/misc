# Return the first non-repeating character in a string or nil if the string
# contains only repeats.

def first_non_repeat_char(s)
  h = Hash.new(0)
  s.each_char { |c| h[c] += 1 unless c == 32 }
  s.each_char { |c| return c if h[c] == 1 }
  return nil
end

# Calculate the "digital root". Repeatedly sum all digits until a single-digit
# sum is reached.

def digital_root(n)
  until n < 10 do
    j = n
    n = 0
    until j < 1 do
      n += j % 10
      j /= 10
    end
  end
  return n
end
