module.exports =  {
    parser:  '@typescript-eslint/parser',  // Specifies the ESLint parser
    extends:  [
      'plugin:@typescript-eslint/recommended',  // Uses the recommended rules from the @typescript-eslint/eslint-plugin
    ],
    parserOptions:  {
      ecmaVersion:  2018,  // Allows for the parsing of modern ECMAScript features
      sourceType:  'module',  // Allows for the use of imports
    },
    rules:  {
        "curly": 0,
        "@typescript-eslint/explicit-function-return-type": [0],
        "@typescript-eslint/no-this-alias": [0],
        "@typescript-eslint/no-unused-vars": [0],
        "@typescript-eslint/no-explicit-any": [0],
        "@typescript-eslint/no-use-before-define": [0],
        "@typescript-eslint/no-empty-function": [0],
        "ordered-imports": [0],
        "object-literal-sort-keys": [0],
        "max-len": [1, 3220],
        "new-parens": 0,
        "no-bitwise": 0,
        "no-cond-assign": 0,
        "no-trailing-spaces": 0,
        "eol-last": 1,
        "func-style": 0,
        "semi": 0,
        "no-var": 0
    },
  };
