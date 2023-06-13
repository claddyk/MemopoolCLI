# kotlin-cli-completion.sh

_kotlin_cli_completion() {
    local cur prev opts subopts
    COMPREPLY=()
    cur="${COMP_WORDS[COMP_CWORD]}"
    prev="${COMP_WORDS[COMP_CWORD-1]}"
    opts="fetchBlockId fetchTxIds"

    if [[ ${cur} == -* ]]; then
        subopts="-s"
        COMPREPLY=( $(compgen -W "${subopts}" -- ${cur}) )
        return 0
    fi

    case "${prev}" in
        fetchBlockId)
            subopts="-s"
            COMPREPLY=( $(compgen -W "${subopts}" -- ${cur}) )
            return 0
            ;;
        fetchTxIds)
            subopts="-s"
            COMPREPLY=( $(compgen -W "${subopts}" -- ${cur}) )
            return 0
            ;;
        *)
            ;;
    esac

    COMPREPLY=($(compgen -W "${opts}" -- ${cur}))
    return 0
}

complete -F _kotlin_cli_completion kotlin-cli

