with import <nixpkgs> {};

stdenv.mkDerivation {
  name = "springboot";

  buildInputs = [
    openjdk11
    gradle_4
  ];
}
